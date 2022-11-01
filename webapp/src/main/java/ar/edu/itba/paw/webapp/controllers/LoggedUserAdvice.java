package ar.edu.itba.paw.webapp.controllers;


import ar.edu.itba.paw.interfaces.services.ProfessorService;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@ControllerAdvice

public class LoggedUserAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedUserAdvice.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessorService professorService;

    @ModelAttribute(value = "loggedUser")
    public User loggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;


        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> loggedUser = userService.findByEmail(username);
        if (!loggedUser.isPresent())
            return null;
        LOGGER.debug("Logged User information requested user: {} ",loggedUser.get().getUserId());
        return loggedUser.get();
    }



    @ModelAttribute(value = "loggedProfessor")
    public Professor loggedProfessor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<Professor> loggedProfessor = professorService.findByEmail(username);
        if (!loggedProfessor.isPresent())
            return null;
        LOGGER.debug("Logged User information requested user: {} ",loggedProfessor.get().getUserId());
        return loggedProfessor.get();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView userNotFound(){
        return new ModelAndView("error/404");
    }
}
