package ar.edu.itba.paw.persistence.jpa;

import ar.edu.itba.paw.interfaces.persistence.ProfessorDao;
import ar.edu.itba.paw.interfaces.persistence.UserDao;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Primary
@Repository
public class ProfessorDaoJpa implements ProfessorDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<Professor> findById(Integer professorId) {
        return Optional.ofNullable(em.find(Professor.class, professorId));
    }


    @Override
    public Optional<Professor> registerProfessor(String name, String surname, String email, String password, String schedule,String phoneNumber, String studies, String description, Professor.Location location) throws UserAlreadyExistsException {
        if(userDao.findByEmail(email).isPresent() || findByEmail(email).isPresent())
            throw new UserAlreadyExistsException();
        final Professor professor = new Professor(null,name,surname,email,password,schedule,phoneNumber,studies,description,location,null);
        em.persist(professor);
        return Optional.of(professor);
    }

    @Override
    public void updateProfessor(Professor professor) {
        em.persist(professor);
    }

    @Override
    public Optional<Professor> findByEmail(String email) {
        final TypedQuery<Professor> query =  em.createQuery("from Professor where email = :email",Professor.class);
        query.setParameter("email",email);
        return query.getResultList().stream().findFirst();
    }

}
