package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.User;

import ar.edu.itba.paw.models.chat.Chat;
import org.thymeleaf.context.Context;
import java.util.Map;
import java.util.Optional;

public interface EmailService {
    void sendSimpleMessage(String to,String subject, String text);
    void sendMessageUsingThymeleafTemplate(String to, String subject, Context thymeleafContext, String templateHTML);
    void sendEmailRegisterUser(User user);
    void sendVerifyEmail(User user);
    void sendNewSubjectRequest(String client, String email, String subject, Subject.Categories category, Subject.Levels level);
    void sendClassChangedStatus(Lesson lesson);
    void sendNewStudent(String email, String student, String subject);
    void sendClassDrop(Lesson lesson);
    void sendNewMessage(Chat chat,Integer userId);
    void sendNewFile(Lesson lesson);
    void sendNewSchedule(Lesson lesson);
}
