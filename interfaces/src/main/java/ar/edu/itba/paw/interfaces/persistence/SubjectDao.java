package ar.edu.itba.paw.interfaces.persistence;

import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.SubjectFilter;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {
    Optional<Subject> findById(Integer id);
    List<Subject> getSubjects(SubjectFilter subjectFilter,int pageNumber,int size);
    List<Subject> getUntaughtSubjects(Integer professorId,SubjectFilter subjectFilter, int pageNumber, int size);
    int getUntaughtSubjectsCount(Integer professorId,SubjectFilter subjectFilter);
    int getSubjectsCount(SubjectFilter subjectFilter);
    List<Subject.Categories> getSubjectsCategories();
    List<Subject.Levels> getSubjectsLevels();
}
