package ar.edu.itba.paw.persistence.jpa;

import ar.edu.itba.paw.interfaces.persistence.UserDao;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;


@Primary
@Repository
public class UserDaoJpa implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final TypedQuery<User> query =  em.createQuery("from User where email = :email",
                User.class);
        query.setParameter("email",email);
        return query.getResultList().stream().findFirst();
    }

    //TODO: Remove optional

    @Override
    public Optional<User> registerUser(String name, String surname, String email, String password, String phoneNumber, boolean isProfessor) throws UserAlreadyExistsException {
        if(findByEmail(email).isPresent())
            throw new UserAlreadyExistsException();
        final User user = new User(null,name,surname,email,password,phoneNumber,isProfessor);
        em.persist(user);
        return Optional.of(user);
    }

    @Override
    public void updateUser(User user) {
        em.persist(user);
    }


}
