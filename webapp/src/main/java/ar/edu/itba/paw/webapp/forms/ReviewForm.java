package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.jetbrains.annotations.Range;

public class ReviewForm {
    @NotEmpty
    private String message;

    @Range(from = 0,to = 5)
    private Integer rating;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
