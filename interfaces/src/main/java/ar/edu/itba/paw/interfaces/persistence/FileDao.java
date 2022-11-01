package ar.edu.itba.paw.interfaces.persistence;


import ar.edu.itba.paw.models.File;

import java.util.List;

public interface FileDao {
    File getFile(Integer fileId);

    void newFile(Integer lessonId, String name, byte[] file);

    void saveFile(File file);

    void deleteFile(Integer fileId);

    int getFilesCount(Integer lessonId);

    List<File> getFiles(Integer lessonId, int pageNumber, int size);
}
