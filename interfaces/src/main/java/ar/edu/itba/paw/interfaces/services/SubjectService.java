package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.SubjectFilter;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<Subject> findById(Integer id);

    int getSubjectsCount(SubjectFilter subjectFilter);

    List<Subject> getSubjects(SubjectFilter subjectFilter, int pageNumber);

    int getUntaughtSubjectsCount(Integer professorId,SubjectFilter subjectFilter);

    List<Subject> getUntaughtSubjects(Integer professorId,SubjectFilter subjectFilter, int pageNumber);
    List<Subject.Categories> getSubjectCategories();
    List<Subject.Levels> getSubjectLevels();
}
