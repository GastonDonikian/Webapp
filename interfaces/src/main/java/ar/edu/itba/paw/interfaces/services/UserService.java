package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;

import java.awt.*;
import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String username);
    Optional<User> registerUser(String name,String surname,String email,String password,String phoneNumber,boolean isProfessor) throws UserAlreadyExistsException;
    void updateUser(Integer userId,String name,String surname,String email,String phoneNumber);
    byte[] getUserImage(Integer userId);
    void setUserImage(Integer userId,byte[] profileImage);
}
