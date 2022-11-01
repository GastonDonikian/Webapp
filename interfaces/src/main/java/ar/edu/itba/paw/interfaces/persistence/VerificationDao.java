package ar.edu.itba.paw.interfaces.persistence;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.VerificationToken;

import java.util.Optional;

public interface VerificationDao {
    Optional<User> verifyUser(String verifyToken);
    VerificationToken createToken(Integer userId);
}
