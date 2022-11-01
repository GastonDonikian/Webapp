package ar.edu.itba.paw.interfaces.persistence;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    Optional<User> registerUser(String name,String surname,String email,String password,String phoneNumber,boolean isProfessor) throws UserAlreadyExistsException;
    void updateUser(User user);
}