package ar.edu.itba.paw.models;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    private Integer userId;

    @OneToOne
    @JoinColumn(name= "user_id",foreignKey = @ForeignKey(name = "verification_user_id_fkey"))
    @MapsId
    @Basic(fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "verification_token",nullable = false,length = 15)
    private String token;

    public VerificationToken(Integer userId, String token, Date expiryDate) {
        this.userId = userId;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    VerificationToken(){

    }
    @Column(name = "expiration_date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
