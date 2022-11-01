package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.persistence.VerificationDao;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.VerificationService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private VerificationDao verificationDao;

    @Override
    @Transactional
    public VerificationToken createToken(Integer userId) {
        return verificationDao.createToken(userId);
    }

    @Override
    @Transactional
    public Optional<User> verifyUser(String verifyToken) {
        return verificationDao.verifyUser(verifyToken);
    }
}
