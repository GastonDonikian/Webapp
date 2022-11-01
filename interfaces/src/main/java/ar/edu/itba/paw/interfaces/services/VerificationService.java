package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.VerificationToken;

import java.util.Optional;

public interface VerificationService {
    VerificationToken createToken(Integer userId);
    Optional<User> verifyUser(String verifyToken);
}
