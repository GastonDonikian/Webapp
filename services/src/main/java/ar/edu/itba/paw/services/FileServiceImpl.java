package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.FileDao;
import ar.edu.itba.paw.interfaces.persistence.LessonDao;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.FileService;
import ar.edu.itba.paw.interfaces.services.LessonService;
import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    final int PAGE_SIZE = 4;
    @Autowired
    private FileDao fileDao;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public File getFile(Integer fileId) {
        return fileDao.getFile(fileId);
    }

    @Override
    @Transactional
    public void newFile(Integer lessonId, String name, byte[] file) {
        fileDao.newFile(lessonId,name,file);
        Optional<Lesson> lessonOptional = lessonDao.findById(lessonId);
        lessonOptional.ifPresent(lesson -> emailService.sendNewFile(lesson));
    }

    @Override
    @Transactional
    public void editFile(Integer fileId, String name, byte[] file) {
        File fileObj = getFile(fileId);
        if(fileObj == null)
            return;
        if(file != null)
            fileObj.setFile(file);
        if(name != null)
            fileObj.setName(name);
        fileDao.saveFile(fileObj);
    }

    @Override
    @Transactional(readOnly = true)
    public int getFilesCount(Integer lessonId){
        return (int) Math.ceil((double) fileDao.getFilesCount(lessonId)/ PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public List<File> getFiles(Integer lessonId, int pageNumber){
        if(pageNumber < 0)
            return fileDao.getFiles(lessonId,1,100);
        return fileDao.getFiles(lessonId,pageNumber,PAGE_SIZE);
    }

    @Override
    @Transactional
    public void deleteFile(Integer fileId) {
        fileDao.deleteFile(fileId);
    }
}
