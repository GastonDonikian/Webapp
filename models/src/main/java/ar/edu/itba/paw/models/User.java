package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @SequenceGenerator( sequenceName = "users_user_id_seq", name = "users_user_id_seq",allocationSize = 1)
    @Column(name = "user_id")
    private Integer userId;

    @Column(nullable = false, length = 30, unique = true)
    private String email;
    @Column(nullable = false, length = 200)
    private String password;


    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String surname;

    @Column(nullable = false,length = 10,name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false,name = "is_professor")
    private boolean isProfessor;
    @Column(nullable = false)
    private boolean enabled;

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean professor) {
        isProfessor = professor;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    @Column(name = "profile_image",nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private byte[] userImage;

    //    Esto se crea pero es solamente para hibernate, visibilidad default
    User() {
        //Just for Hibernate
    }

    public User(final Integer userId, final String name, final String surname, final String email, final String password, final String phoneNumber, boolean isProfessor) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isProfessor = isProfessor;
    }

    public User(final Integer userId, final String name, final String surname, final String email, final String password, final String phoneNumber, boolean isProfessor, boolean enabled) {
        this(userId, name, surname, email, password, phoneNumber, isProfessor);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsProfessor() {
        return isProfessor;
    }

    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}