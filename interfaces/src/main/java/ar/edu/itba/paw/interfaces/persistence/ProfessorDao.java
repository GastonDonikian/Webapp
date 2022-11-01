package ar.edu.itba.paw.interfaces.persistence;

import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface ProfessorDao {
    Optional<Professor> findById(Integer professorId);
    Optional<Professor>  registerProfessor(String name, String surname, String email, String password, String schedule,String phoneNumber, String studies, String description, Professor.Location location) throws UserAlreadyExistsException;
    void updateProfessor(Professor professor);
    Optional<Professor> findByEmail(String email);
}
