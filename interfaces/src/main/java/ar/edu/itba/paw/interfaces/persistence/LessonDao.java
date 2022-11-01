package ar.edu.itba.paw.interfaces.persistence;

import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.LessonFilter;

import java.util.List;
import java.util.Optional;

public interface LessonDao {
    List<Lesson> getLessons(LessonFilter lessonFilter,int pageNumber,int size);

    int getLessonsCount(LessonFilter lessonFilter);

    void changeLesson(Lesson lesson);

    Lesson newLesson(Integer subjectId, Integer professorId, Integer studentId, Float price, Lesson.LessonStatus status, Boolean confirmedByProfessor, Boolean confirmedByStudent);

    Optional<Lesson> findById(Integer lessonId);
    //    Aca podemos usar lessonId porque es interno
    void dropLesson(Integer lessonId);
}
