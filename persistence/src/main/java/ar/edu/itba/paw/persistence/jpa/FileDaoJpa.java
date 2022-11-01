package ar.edu.itba.paw.persistence.jpa;

import ar.edu.itba.paw.interfaces.persistence.FileDao;
import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.Lesson;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;


@Primary
@Repository
public class FileDaoJpa implements FileDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public File getFile(Integer fileId) {
        return em.find(File.class,fileId);
    }

    @Override
    public void newFile(Integer lessonId, String name, byte[] file) {
        Lesson lesson = em.getReference(Lesson.class,lessonId);
        em.persist(new File(null,name,file,lesson));
    }

    @Override
    public void saveFile(File file) {
        em.persist(file);
    }

    @Override
    public List<File> getFiles(Integer lessonId,int pageNumber, int size){
        Query nativeQuery = queryGetFiles("SELECT file_id",lessonId,true,pageNumber,size);
        final List<Integer> fileIdList = ((List<Integer>) nativeQuery.getResultList());
        if(fileIdList.isEmpty())
            return Collections.emptyList();
        final TypedQuery<File> query = em.createQuery("from File where fileId IN :fileIdList",File.class);
        query.setParameter("fileIdList",fileIdList);
        return query.getResultList();
    }

    @Override
    public int getFilesCount(Integer lessonId){
        Query nativeQuery = queryGetFiles("SELECT file_id",lessonId,false,0,0);
        return nativeQuery.getResultList().size();
    }

    private Query queryGetFiles(String preString,Integer lessonId,boolean orderAndPage,int pageNumber,int size){
        StringBuilder query = new StringBuilder(preString);
        query.append(" FROM files WHERE lesson_id = :lessonId");
        Map<String, Object> params = new HashMap<>();
        params.put("lessonId",lessonId);
        if(orderAndPage){
            int skip = 0;
            if(pageNumber != 1) {
                skip = (pageNumber - 1) * size;
            }
            query.append(" LIMIT :size OFFSET :skip ");
            params.put("size",size);
            params.put("skip",skip);
        }

        final Query nativeQuery = em.createNativeQuery(query.toString());
        for(Map.Entry<String,Object> entry : params.entrySet()) {
            nativeQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return nativeQuery;

    }

    @Override
    public void deleteFile(Integer fileId) {
        em.remove(em.getReference(File.class,fileId));
    }
}
