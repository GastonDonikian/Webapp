package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class MessageForm {
    @NotEmpty@Size(min = 1)
    private String message;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
