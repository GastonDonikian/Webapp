package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.LessonFilter;

import java.util.List;

public interface LessonService {

    int getLessonsCount(LessonFilter lessonFilter);

    int getLessonsPages(LessonFilter lessonFilter);

    List<Lesson> getLessons(LessonFilter lessonFilter,Integer pageNumber);

    void updateClassroom(Integer lessonId, String schedule, String meetingLink);

    void changeLessonStatus(Integer lessonId, boolean isProfessor, Lesson.LessonStatus status);

    void newLesson(Integer subjectId, Integer professorId, Integer studentId, Float price, Lesson.LessonStatus status, Boolean confirmedByProfessor, Boolean confirmedByStudent);

    void dropLesson(Integer lessonId);
}
