package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
        Optional<Professor> findById(Integer id);
        Optional<Professor> findByEmail(String email);
        Optional<Professor>  registerProfessor(String name,String surname,String email,String password,String schedule,String phoneNumber,String studies, String description,Professor.Location location) throws UserAlreadyExistsException;
        void updateProfessor(Integer professorId,String name,String surname,String email,String schedule,String phoneNumber,String studies,String description);
       }
