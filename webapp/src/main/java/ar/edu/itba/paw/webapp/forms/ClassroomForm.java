package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;

public class ClassroomForm {

    @NotEmpty@Size(max = 30)
    private String schedule;
    @NotEmpty@Size(max = 30)
    private String meetingLink;

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getMeetingLink() {
        return meetingLink;
    }
}
