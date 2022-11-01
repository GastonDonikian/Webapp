package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.ProfessorService;
import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.forms.NewSubjectForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import javax.validation.Valid;

@Controller
public class AdministrativeController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoggedUserAdvice loggedUserAdvice;

    @Autowired
    private ProfessorService professorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministrativeController.class);


    @RequestMapping(path = "/nuevaMateria", method = RequestMethod.GET)
    public ModelAndView newSubject(@ModelAttribute("newSubjectForm") final NewSubjectForm form){
        final ModelAndView mav = new ModelAndView("nuevaMateria");
        mav.addObject("categories", Subject.Categories.values());
        mav.addObject("levels",Subject.Levels.values());
        LOGGER.debug("New Subject form requested");
        return mav;
    }

    @RequestMapping(path = "/nuevaMateria", method = RequestMethod.POST)
    public ModelAndView newSubject(@ModelAttribute("newSubjectForm")@Valid final NewSubjectForm form, final BindingResult errors){
        if(errors.hasErrors())
            return newSubject(form);
        User user = loggedUserAdvice.loggedUser();
        LOGGER.debug("User {} requests new subject", user.getUserId());
        emailService.sendNewSubjectRequest(user.getName() + user.getSurname(), user.getEmail(),form.getSubject(),form.getCategory(),form.getLevel());
        return new ModelAndView("redirect:/");
    }

}
