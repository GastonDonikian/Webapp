package ar.edu.itba.paw.webapp.controllers.account;

import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.ProfessorService;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.interfaces.services.VerificationService;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.exceptions.UserAlreadyExistsException;
import ar.edu.itba.paw.webapp.auth.PawUserDetailsService;
import ar.edu.itba.paw.webapp.forms.SignUpProfessorForm;
import ar.edu.itba.paw.webapp.forms.SignUpStudentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class NotLoggedController {


    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PawUserDetailsService pawUserDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotLoggedController.class);

    @RequestMapping(path = "/register") // ESTE LLAMA A LA PAGINA
    public ModelAndView studentOrProfessor() {
        return new ModelAndView("registracion/studentOrProfessor");
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/registracion/login");
    }

    @RequestMapping(path = "/registerProfessor", method = RequestMethod.GET) // ESTE LLAMA A LA PAGINA
    public ModelAndView registerProfessor(@ModelAttribute("SignUpProfessorForm") final SignUpProfessorForm form, @RequestParam(value = "error",required = false) final boolean userAlreadyExists) {
        ModelAndView mav = new ModelAndView("registracion/registerProfessor");
        mav.addObject("userAlreadyExists",userAlreadyExists);
        mav.addObject("locations",Professor.Location.values());
        LOGGER.debug("Register professor form being requested");

        return mav;
    }

    @RequestMapping(path = "/registerProfessor", method = RequestMethod.POST)
    public ModelAndView signUpProfessor(@ModelAttribute("SignUpProfessorForm") @Valid final SignUpProfessorForm form, final BindingResult errors) {
        if (errors.hasErrors())
            return registerProfessor(form,false);
        Optional<Professor> prof;
        try {
            prof = professorService.registerProfessor(form.getName(), form.getSurname(), form.getEmail(), form.getPassword(),form.getSchedule(), form.getPhone(), form.getStudies(), form.getDescription(),form.getLocation());
        } catch (UserAlreadyExistsException e) {
            return registerProfessor(form,true);
        }
        if(prof.isPresent()){
            autoLogin(prof.get().getEmail());
            return new ModelAndView("redirect:/verify/");

        }
        LOGGER.debug("Register professor form being submitted");
        return new ModelAndView("redirect:/error500");
    }

    @RequestMapping(path = "/registerStudent", method = RequestMethod.GET) // ESTE LLAMA A LA PAGINA
    public ModelAndView signUpStudent(@ModelAttribute("SignUpStudent") final SignUpStudentForm form, @RequestParam(value = "error",required = false) final boolean userAlreadyExists) {
        //Lo hicimos asi para ser consistentes con spring security
        ModelAndView mav = new ModelAndView("registracion/registerStudent");
        mav.addObject("userAlreadyExists",userAlreadyExists);

        LOGGER.debug("Register student form being requested");
        return mav;
    }

    @RequestMapping(path = "/registerStudent", method = RequestMethod.POST)
    public ModelAndView signUpStudent(@ModelAttribute("SignUpStudent") @Valid final SignUpStudentForm form, final BindingResult errors) {
        if (errors.hasErrors())
            return signUpStudent(form,false);
        Optional<User> user;
        try {
            user = userService.registerUser(form.getName(), form.getSurname(), form.getEmail(), form.getPassword(), form.getPhone(), false);
        } catch (UserAlreadyExistsException e){
            return signUpStudent(form,true);
        }

        if(user.isPresent()){
            autoLogin(user.get().getEmail());
            return new ModelAndView("redirect:/verify/");
        }

        LOGGER.debug("Register student form being submited");
        return new ModelAndView("redirect:/error500");
    }

    private void autoLogin(String email){
        UserDetails userDetails = pawUserDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOGGER.debug("Autologging a user with email: {}",email);
    }
}
