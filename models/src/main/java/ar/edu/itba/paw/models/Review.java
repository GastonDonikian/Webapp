package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_review_id_seq")
    @SequenceGenerator( sequenceName = "review_review_id_seq", name = "review_review_id_seq",allocationSize = 1)
    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "rating")
    private Integer rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",foreignKey = @ForeignKey(name = "review_lesson_id_fkey"))
    private Lesson lesson;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,foreignKey = @ForeignKey(name = "review_user_id_fkey"))
    private User author;

    @Column(name = "date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "message",length = 255,nullable = false)
    private String message;

    public Integer getReviewId() {
        return reviewId;
    }

    public Integer getRating() {
        return rating;
    }


    Review(){

    }

    public Review(Integer reviewId, Integer rating) {
        this.reviewId = reviewId;
        this.rating = rating;
    }

    public Review(Integer reviewId,Lesson lesson,Integer rating){
        this.reviewId = reviewId;
        this.lesson = lesson;
        this.rating = rating;
    }

    public Review(Integer reviewId, Lesson lesson, User author, Date date, String message, Integer rating) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.lesson = lesson;
        this.author = author;
        this.date = date;
        this.message = message;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Lesson getLesson() {

        return lesson;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
