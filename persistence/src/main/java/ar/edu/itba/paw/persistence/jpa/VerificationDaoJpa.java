package ar.edu.itba.paw.persistence.jpa;


import ar.edu.itba.paw.interfaces.persistence.UserDao;
import ar.edu.itba.paw.interfaces.persistence.VerificationDao;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

@Primary
@Repository
public class VerificationDaoJpa implements VerificationDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    private Optional<VerificationToken> findByToken(String token) {
        final TypedQuery<VerificationToken> query =  em.createQuery("from VerificationToken where token = :token",
                VerificationToken.class);
        query.setParameter("token",token);
        return query.getResultList().stream().findFirst();
    }


    private void deleteToken(VerificationToken token){
        em.remove(token);
    }

    private void persistToken(VerificationToken token){
        em.persist(token);
    }

    @Override
    public Optional<User> verifyUser(String verifyToken) {
        Optional<VerificationToken> optionalVerificationToken = findByToken(verifyToken);
        if(!optionalVerificationToken.isPresent())
            return Optional.empty();
        VerificationToken verificationToken = optionalVerificationToken.get();
        verificationToken.getUser().setEnabled(true);
        userDao.updateUser(verificationToken.getUser());
        deleteToken(verificationToken);
        return Optional.of(verificationToken.getUser());
    }

    @Override
    public VerificationToken createToken(Integer userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if(!optionalUser.isPresent())
            return null; //TODO: LOGGEAR UN WTF!
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        Date date = new Date(calendar.getTime().getTime());
        final VerificationToken verificationToken = new VerificationToken(null,randomToken(15),date);
        verificationToken.setUser(optionalUser.get());
        persistToken(verificationToken);
        return verificationToken;
    }


    private String randomToken(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}
