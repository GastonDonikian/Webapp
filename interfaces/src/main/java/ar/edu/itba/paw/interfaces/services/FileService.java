package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.File;

import java.util.List;

public interface FileService {
    File getFile(Integer fileId);
    void newFile(Integer lessonId,String name,byte[] file);
    void editFile(Integer fileId,String name, byte[] file);
    void deleteFile(Integer fileId);
    List<File> getFiles(Integer lessonId, int pageNumber);
    int getFilesCount(Integer lessonId);

    }
