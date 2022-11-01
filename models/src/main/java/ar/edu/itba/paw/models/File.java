package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "files_file_id_seq")
    @SequenceGenerator( sequenceName = "files_file_id_seq", name = "files_file_id_seq",allocationSize = 1)
    @Column(name = "file_id")
    private Integer fileId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(name = "file",nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",foreignKey = @ForeignKey(name = "files_lesson_id_fkey"))
    private Lesson lesson;

    File(){}

    public File(Integer fileId, String name, byte[] file, Lesson lesson) {
        this.fileId = fileId;
        this.name = name;
        this.file = file;
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
