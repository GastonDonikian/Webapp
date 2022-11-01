package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.LessonDao;
import ar.edu.itba.paw.interfaces.persistence.ProfessorDao;
import ar.edu.itba.paw.interfaces.services.ChatService;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.LessonService;
import ar.edu.itba.paw.interfaces.services.ProfessorService;
import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.LessonFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    final int PAGE_SIZE = 4;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ChatService chatService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LessonServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessons(LessonFilter lessonFilter,Integer pageNumber) {
        return lessonDao.getLessons(lessonFilter, pageNumber, PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public int getLessonsCount(LessonFilter lessonFilter) {
        return (int) Math.ceil((double) lessonDao.getLessonsCount(lessonFilter));
    }

    @Override
    @Transactional(readOnly = true)
    public int getLessonsPages(LessonFilter lessonFilter) {
        return (int) Math.ceil((double) lessonDao.getLessonsCount(lessonFilter) / PAGE_SIZE);
    }

    @Override
    @Transactional
    public void updateClassroom(Integer lessonId, String schedule, String meetingLink){
        Optional<Lesson> optionalLesson = lessonDao.findById(lessonId);
        if(!optionalLesson.isPresent())
            return;
        Lesson lesson = optionalLesson.get();
        if(schedule != null && !schedule.isEmpty())
        lesson.setSchedule(schedule);
        if(meetingLink != null && !meetingLink.isEmpty())
            lesson.setMeetingLink(meetingLink);
        emailService.sendNewSchedule(lesson);
        lessonDao.changeLesson(lesson);
    }

    @Override
    @Transactional
    public void changeLessonStatus(Integer lessonId, boolean isProfessor, Lesson.LessonStatus status) {
        Optional<Lesson> lessonOptional = lessonDao.findById(lessonId);
        if (!lessonOptional.isPresent())
            return;
        Lesson lesson = lessonOptional.get();
        if(lesson.getLessonStatus() == Lesson.LessonStatus.FINISHED){
            newLesson(lesson.getSubject().getId(),lesson.getProfessor().getUserId(),lesson.getStudent().getUserId(), lesson.getPrice(),status,isProfessor,!isProfessor);

        }
        else {
            lesson.setLessonStatus(status);
            lesson.setConfirmedByProfessor(isProfessor);
            lesson.setConfirmedByStudent(!isProfessor);
            lessonDao.changeLesson(lesson);
            emailService.sendClassChangedStatus(lesson);
        }
    }

    @Override
    @Transactional
    public void newLesson(Integer subjectId, Integer professorId, Integer studentId, Float price, Lesson.LessonStatus status, Boolean confirmedByProfessor, Boolean confirmedByStudent) {
        if (lessonDao.getLessons(new LessonFilter.Builder().subject(subjectId).status(Lesson.LessonStatus.IN_PROCESS).professor(professorId).student(studentId).build(), 1, 10).isEmpty()) {
            if (lessonDao.getLessons(new LessonFilter.Builder().subject(subjectId).status(Lesson.LessonStatus.PENDING_APPROVAL).professor(professorId).student(studentId).build(), 1, 10).isEmpty()) {
                Lesson lesson = lessonDao.newLesson(subjectId, professorId, studentId, price, status, confirmedByProfessor, confirmedByStudent);
                emailService.sendNewStudent(lesson.getProfessor().getEmail(),lesson.getStudent().getName(),lesson.getSubject().getName());
            }
        }

    }

    @Override
    @Transactional
    public void dropLesson(Integer lessonId) {
        Optional<Lesson> lesson = lessonDao.findById(lessonId);
        if(lesson.isPresent())
            emailService.sendClassDrop(lesson.get());
        lessonDao.dropLesson(lessonId);

    }

}
