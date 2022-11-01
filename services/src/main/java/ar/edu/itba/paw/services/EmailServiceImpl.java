package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.VerificationService;
import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.chat.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

@Service
@EnableAsync
public class EmailServiceImpl implements EmailService {
    @Autowired
    private VerificationService verificationService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine thymeleafTemplateEngine;

    @Async
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            emailSender.send(message);
        }catch(Exception ignored){
        }
    }

    @Async
    @Override
    public void sendEmailRegisterUser(User user){
        Context thymeleafContext = new Context();
        String string;
        String client;
        client = user.getName() + " " + user.getSurname();
        thymeleafContext.setVariable("client", client);
        thymeleafContext.setVariable("email", user.getEmail());
        string = "http://pawserver.it.itba.edu.ar/paw-2021b-8/verify/" + verificationService.createToken(user.getUserId()).getToken();
        thymeleafContext.setVariable("token", string);
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(user.getEmail(), messages.getString("subject.validate"), thymeleafContext, "emailVerificationStudent.html");
    }

    @Async
    @Override
    public void sendVerifyEmail(User user){
        Context thymeleafContext = new Context();
        String string;
        String client;

        client = user.getName() + " " + user.getSurname();
        thymeleafContext.setVariable("client",client);
        thymeleafContext.setVariable("email",user.getEmail());
        string = "https://pawserver.it.itba.edu.ar/paw-2021b-8/verify/"+ verificationService.createToken(user.getUserId()).getToken();
        thymeleafContext.setVariable("token", string);
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(user.getEmail(), messages.getString("subject.validate"), thymeleafContext, "emailVerificationStudent.html");
    }

    @Async
    @Override
    public void sendNewSubjectRequest(String client, String email, String subject, Subject.Categories category, Subject.Levels level){
        org.thymeleaf.context.Context thymeleafContext = new Context();
        String string;
        thymeleafContext.setVariable("client",client);
        thymeleafContext.setVariable("email",email);
        thymeleafContext.setVariable("subject",subject);
        thymeleafContext.setVariable("category",category);
        thymeleafContext.setVariable("level",level);
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(email,messages.getString("subject.newStudent"), thymeleafContext, "emailNewSubject.html");
    }

    @Async
    @Override
    public void sendClassChangedStatus(Lesson lesson){
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("professor", lesson.getProfessor().getName());
        thymeleafContext.setVariable("email", lesson.getProfessor().getEmail());
        thymeleafContext.setVariable("subject", lesson.getSubject().getName());
        thymeleafContext.setVariable("status", lesson.getLessonStatus().name());
        thymeleafContext.setVariable("token", "http://pawserver.it.itba.edu.ar/paw-2021b-8/myLessons");
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(lesson.getStudent().getEmail(), messages.getString("subject.statusChanged"), thymeleafContext, "emailClassChangedStatus.html");
        sendMessageUsingThymeleafTemplate(lesson.getProfessor().getEmail(), messages.getString("subject.statusChanged"), thymeleafContext, "emailClassChangedStatus.html");
    }

    @Async
    @Override
    public void sendClassDrop(Lesson lesson){
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("professor", lesson.getProfessor().getName());
        thymeleafContext.setVariable("professorEmail", lesson.getProfessor().getEmail());
        thymeleafContext.setVariable("student", lesson.getStudent().getName());
        thymeleafContext.setVariable("studentEmail", lesson.getStudent().getEmail());
        thymeleafContext.setVariable("subject", lesson.getSubject().getName());
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(lesson.getStudent().getEmail(), messages.getString("subject.cancelledClass"), thymeleafContext, "emailClassCancelled.html");
        sendMessageUsingThymeleafTemplate(lesson.getProfessor().getEmail(), messages.getString("subject.cancelledClass"), thymeleafContext, "emailClassCancelled.html");
    }

    @Override
    public void sendNewStudent(String email, String student, String subject) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("student", student);
        thymeleafContext.setVariable("subject", subject);
        thymeleafContext.setVariable("token", "http://pawserver.it.itba.edu.ar/paw-2021b-8/myStudents");
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(email, messages.getString("subject.newStudent"), thymeleafContext, "emailNewStudent.html");
    }

    @Async
    @Override
    public void sendNewMessage(Chat chat,Integer userId){
        Lesson lesson = chat.getLesson();
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("professor",lesson.getProfessor().getName());
        thymeleafContext.setVariable("student",lesson.getStudent().getName());
        thymeleafContext.setVariable("subject",lesson.getSubject().getName());
        thymeleafContext.setVariable("token","http://pawserver.it.itba.edu.ar/paw-2021b-8/class/" + lesson.getLessonId());
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        //email a prof
        if (Objects.equals(userId, lesson.getStudent().getUserId()))
        {
            sendMessageUsingThymeleafTemplate(lesson.getProfessor().getEmail(), messages.getString("subject.newMessage"), thymeleafContext, "emailNewMessage.html");
        }
        //email a alumno
        else if(Objects.equals(userId, lesson.getProfessor().getUserId()))
        {
            sendMessageUsingThymeleafTemplate(lesson.getStudent().getEmail(), messages.getString("subject.newMessage"), thymeleafContext, "emailNewMessage.html");
        }
    }

    @Async
    @Override
    public void sendNewFile(Lesson lesson){
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("professor",lesson.getProfessor().getName());
        thymeleafContext.setVariable("subject",lesson.getSubject().getName());
        thymeleafContext.setVariable("token","http://pawserver.it.itba.edu.ar/paw-2021b-8/class/" + lesson.getLessonId());
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(lesson.getStudent().getEmail(), messages.getString("subject.newFile"), thymeleafContext, "emailNewFile.html");
    }

    @Async
    @Override
    public void sendNewSchedule(Lesson lesson){
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("professor",lesson.getProfessor().getName());
        thymeleafContext.setVariable("subject",lesson.getSubject().getName());
        thymeleafContext.setVariable("token","http://pawserver.it.itba.edu.ar/paw-2021b-8/class/" + lesson.getLessonId());
        ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
        sendMessageUsingThymeleafTemplate(lesson.getStudent().getEmail(), messages.getString("subject.newSchedule"), thymeleafContext, "emailNewSchedule.html");
    }

    @Async
    @Override
    public void sendMessageUsingThymeleafTemplate(
            String to, String subject, Context thymeleafContext, String templateHTML) {
        String htmlBody = thymeleafTemplateEngine.process(templateHTML, thymeleafContext);
        try
        {
            sendHtmlMessage(to, subject, htmlBody);
        } catch (MessagingException ignored)
        {
        }
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException
    {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }

}
