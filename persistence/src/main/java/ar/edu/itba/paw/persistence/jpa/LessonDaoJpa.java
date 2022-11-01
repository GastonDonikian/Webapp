package ar.edu.itba.paw.persistence.jpa;


import ar.edu.itba.paw.interfaces.persistence.ChatDao;
import ar.edu.itba.paw.interfaces.persistence.LessonDao;
import ar.edu.itba.paw.models.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LessonDaoJpa implements LessonDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ChatDao chatDao;

    @Override
    public List<Lesson> getLessons(LessonFilter lessonFilter, int pageNumber, int size) {
        final Query nativeQuery = queryGetLessons("SELECT lesson_id ",lessonFilter,true,pageNumber,size);

        final List<Integer> lessonIdList = ((List<Integer>) nativeQuery.getResultList());

        if(lessonIdList.isEmpty())
            return Collections.emptyList();
        final TypedQuery<Lesson> query = em.createQuery("from Lesson where lessonId IN :lessonIdList",Lesson.class);
        query.setParameter("lessonIdList",lessonIdList);
        return query.getResultList();
    }

    private Query queryGetLessons(String preString, final LessonFilter lessonFilter,boolean orderAndPage,int pageNumber, int size){
        StringBuilder query = new StringBuilder(preString);
        query.append(" FROM full_lesson WHERE 1=1 ");
        Map<String,Object> params = new HashMap<>();

        if(lessonFilter != null){
            if(lessonFilter.getLessonId() != -1){
                query.append("AND lesson_id = :lessonId ");
                params.put("lessonId",lessonFilter.getLessonId());
            }
            if(lessonFilter.getProfessorId() != -1){
                query.append("AND professor_id = :professorId ");
                params.put("professorId",lessonFilter.getProfessorId());
            }
            if(lessonFilter.getStudentId() != -1){
                query.append("AND student_id = :studentId ");
                params.put("studentId",lessonFilter.getStudentId());
            }
            if(lessonFilter.getSubjectId() != -1){
                query.append("AND subject_id = :subjectId ");
                params.put("subjectId",lessonFilter.getSubjectId());
            }
            if(lessonFilter.getStatus() != null){
                query.append("AND lesson_status = :lessonStatus ");
                params.put("lessonStatus",lessonFilter.getStatus().name());
            }
            if(lessonFilter.getConfirmedByStudent() != null){
                query.append("AND confirmed_by_student = :confirmedByStudent ");
                params.put("confirmedByStudent",lessonFilter.getConfirmedByStudent());
            }
            if(lessonFilter.getConfirmedByProfessor() != null){
                query.append("AND confirmed_by_professor = :confirmedByProfessor ");
                params.put("confirmedByProfessor",lessonFilter.getConfirmedByProfessor());
            }
            if (orderAndPage) {
                int skip = 0;
                if(pageNumber != 1) {
                    skip = (pageNumber - 1) * size;
                }
                if (lessonFilter.getOrderBy() == LessonFilter.OrderBy.OLDEST)
                    query.append("ORDER BY lesson_id DESC");
                else if (lessonFilter.getOrderBy() == LessonFilter.OrderBy.RECENT)
                    query.append("ORDER BY lesson_id ASC");
                query.append(" LIMIT :size OFFSET :skip ");
                params.put("size",size);
                params.put("skip",skip);
            }

        }
        final Query nativeQuery = em.createNativeQuery(query.toString());
        for(Map.Entry<String,Object> entry : params.entrySet()) {
            nativeQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return nativeQuery;
    }

    @Override
    public int getLessonsCount(LessonFilter lessonFilter) {
        final Query queryCount = queryGetLessons("SELECT * ",lessonFilter,false,0,0);
        return queryCount.getResultList().size();
    }

    @Override
    public Optional<Lesson> findById(Integer lessonId){
        return Optional.ofNullable(em.find(Lesson.class, lessonId));
    }
    @Override
    public void changeLesson(Lesson lesson) {
        em.persist(lesson);
    }

    @Override
    public Lesson newLesson(Integer subjectId, Integer professorId, Integer studentId, Float price, Lesson.LessonStatus status, Boolean confirmedByProfessor, Boolean confirmedByStudent) {

        final Professor professor = em.getReference(Professor.class, professorId);
        final User student = em.getReference(User.class,studentId);
        final Subject subject = em.getReference(Subject.class, subjectId);

        final Lesson lesson = new Lesson(null,subject,professor,student,price,status,confirmedByStudent,confirmedByProfessor,null);
        em.persist(lesson);
        chatDao.newChat(lesson.getLessonId(), "Chat");
        lesson.setChat(chatDao.getChatByLesson(lesson.getLessonId()));
        em.persist(lesson);
        return lesson;
    }

    @Override
    public void dropLesson(Integer lessonId) {
        if(!findById(lessonId).isPresent())
            return;
        final Lesson lesson = em.getReference(Lesson.class, lessonId);
        em.remove(lesson);
    }
}
