package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.ProfessorDao;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.ProfessorService;
import ar.edu.itba.paw.interfaces.services.VerificationService;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Professor> findById(Integer id) {
        return professorDao.findById(id);
    }

    @Override
    @Transactional
    public Optional<Professor>  registerProfessor(String name,String surname,String email,String password,String schedule,String phoneNumber,String studies, String description,Professor.Location location) throws UserAlreadyExistsException {
        Optional<Professor> professor = professorDao.registerProfessor(name, surname, email, passwordEncoder.encode(password), schedule,phoneNumber, studies, description, location);
        professor.ifPresent(value -> emailService.sendEmailRegisterUser(value));

        return professor;
    }

    @Override
    @Transactional(readOnly = true)
    public  Optional<Professor> findByEmail(String email) {
        return professorDao.findByEmail(email);
    }

    @Override
    @Transactional
    public void updateProfessor(Integer professorId,String name,String surname,String email,String schedule,String phoneNumber, String studies,String description){
        Optional<Professor> optionalProfessor = professorDao.findById(professorId);
        if(!optionalProfessor.isPresent())
            return;
        Professor professor = optionalProfessor.get();
        professor.setName(name);
        professor.setSurname(surname);
        professor.setSchedule(schedule);
        professor.setPhoneNumber(phoneNumber);
        professor.setStudies(studies);
        professor.setDescription(description);
        professorDao.updateProfessor(professor);
    }
}
