package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactProfessorForm {
    private Integer professorId;

    @NotEmpty()
    private String from;
    @NotEmpty()@Email()
    private String email;
    @Size(min = 8,max = 13)@Pattern(regexp = "[0-9]+")
    private String phoneNumber;
    @NotEmpty()
    private String subject;
    @NotEmpty()
    private String message;

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
