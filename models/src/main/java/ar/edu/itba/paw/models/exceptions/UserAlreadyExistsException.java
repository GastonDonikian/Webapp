package ar.edu.itba.paw.models.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("User already exists.");
    }
}
