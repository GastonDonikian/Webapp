package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.UserDao;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.interfaces.services.VerificationService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public Optional<User> registerUser(String name, String surname, String email, String password, String phoneNumber, boolean isProfessor) throws UserAlreadyExistsException {
        Optional<User> user = userDao.registerUser(name, surname, email, passwordEncoder.encode(password), phoneNumber, isProfessor);
        if(user.isPresent()) {
            emailService.sendEmailRegisterUser(user.get());
        }
        return user;
    }

    @Override
    @Transactional
    public void updateUser(Integer userId, String name, String surname, String email, String phoneNumber) {
        Optional<User> optionalUser = userDao.findById(userId);
        if(!optionalUser.isPresent())
            return;
        User user = optionalUser.get();
        user.setName(name);
        user.setSurname(surname);
        //FixMe NO SE DEBERIA PODER CAMBIAR EL EMAIL; VER SI LO SACAMOS O SI EN ALGUN MOMENTO SIRVE
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getUserImage(Integer userId) {
        final Optional<User> optionalUser = userDao.findById(userId);
        return optionalUser.map(User::getUserImage).orElse(null);
    }

    @Override
    @Transactional
    public void setUserImage(Integer userId, byte[] profileImage) {
        if (profileImage == null || profileImage.length == 0)
            return;
        final Optional<User> optionalUser = userDao.findById(userId);
        if(!optionalUser.isPresent())
            return;
        final User user = optionalUser.get();
        user.setUserImage(profileImage);
        userDao.updateUser(user);
    }
}
