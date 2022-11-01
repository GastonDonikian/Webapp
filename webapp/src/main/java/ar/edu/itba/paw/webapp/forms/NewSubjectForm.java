package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.Subject;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class NewSubjectForm {
    @NotEmpty()
    private String subject;
    @NotEmpty()@Email()
    private String email;
    @NotEmpty()
    private String message;
    private Subject.Levels level;
    private Subject.Categories category;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Subject.Levels getLevel() {
        return level;
    }

    public void setLevel(Subject.Levels level) {
        this.level = level;
    }

    public Subject.Categories getCategory() {
        return category;
    }

    public void setCategory(Subject.Categories category) {
        this.category = category;
    }
}
