package ar.edu.itba.paw.models;

import ar.edu.itba.paw.models.chat.Chat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "lesson")
public class Lesson {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_lesson_id_seq")
    @SequenceGenerator( sequenceName = "lesson_lesson_id_seq", name = "lesson_lesson_id_seq",allocationSize = 1)
    @Column(name = "lesson_id")
    private Integer lessonId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name ="subject_id",foreignKey = @ForeignKey(name = "lesson_subject_id_fkey"))
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professor_id",foreignKey = @ForeignKey(name = "lesson_professor_id_fkey"))
    private Professor professor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", referencedColumnName = "user_id",foreignKey = @ForeignKey(name = "lesson_student_id_fkey"))
    private User student;

    @OneToOne(mappedBy = "lesson",fetch = FetchType.LAZY)
    private Review review;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",foreignKey = @ForeignKey(name = "chat_lesson_id_fkey"))
    private Chat chat;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "lesson")
    private Set<File> fileList;
    
    @Column
    private float price;

    @Column(name="lesson_status",nullable = false,length = 20)
    @Enumerated(EnumType.STRING)
    private LessonStatus lessonStatus;

    @Column(name="confirmed_status_by_student")
    private boolean confirmedByStudent;

    @Column(name="confirmed_status_by_professor")
    private boolean confirmedByProfessor;

    @Column(name ="schedule")
    private String schedule;

    @Column(name = "meeting_link")
    private String meetingLink;

    public enum LessonStatus{
        PENDING_APPROVAL,IN_PROCESS,PENDING_PAYMENT,FINISHED,CANCELLED
    }

    Lesson(){

    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public List<File> getFileList() {
        return new ArrayList<>(fileList);
    }

    public void setFileList(Set<File> fileList) {
        this.fileList = fileList;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public Review getReview() {
        return review;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public Lesson(Integer lessonId, Subject subject, Professor professor, User student, Float price, LessonStatus lessonStatus, Boolean confirmedByStudent, Boolean confirmedByProfessor, Review review) {
        this.lessonId = lessonId;
        this.subject = subject;
        this.professor = professor;
        this.student = student;
        this.price = price;
        this.lessonStatus = lessonStatus;
        this.confirmedByStudent = confirmedByStudent;
        this.confirmedByProfessor = confirmedByProfessor;
        this.review = review;
    }

    public Integer getLessonId()
    {
        return lessonId;
    }

    public void setLessonId(Integer lessonId)
    {
        this.lessonId = lessonId;
    }

    public Subject getSubject()
    {
        return subject;
    }

    public void setSubject(Subject subject)
    {
        this.subject = subject;
    }

    public Professor getProfessor()
    {
        return professor;
    }

    public void setProfessor(Professor professor)
    {
        this.professor = professor;
    }

    public User getStudent()
    {
        return student;
    }

    public void setStudent(User student)
    {
        this.student = student;
    }

    public LessonStatus getLessonStatus()
    {
        return lessonStatus;
    }

    public void setLessonStatus(LessonStatus lessonStatus)
    {
        this.lessonStatus = lessonStatus;
    }

    public boolean isConfirmedByStudent()
    {
        return confirmedByStudent;
    }

    public void setConfirmedByStudent(boolean confirmedByStudent)
    {
        this.confirmedByStudent = confirmedByStudent;
    }

    public boolean isConfirmedByProfessor()
    {
        return confirmedByProfessor;
    }

    public void setConfirmedByProfessor(boolean confirmedByProfessor)
    {
        this.confirmedByProfessor = confirmedByProfessor;
    }
}
