package ar.edu.itba.paw.webapp.controllers.account;

import ar.edu.itba.paw.interfaces.services.*;
import ar.edu.itba.paw.models.Contract;
import ar.edu.itba.paw.models.ContractFilter;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.controllers.LoggedUserAdvice;
import ar.edu.itba.paw.webapp.auth.PawUserDetailsService;
import ar.edu.itba.paw.webapp.forms.ProfileProfessorForm;
import ar.edu.itba.paw.webapp.forms.ProfileStudentForm;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;


@Controller
public class LoggedController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PawUserDetailsService pawUserDetailsService;

    @Autowired
    private LoggedUserAdvice loggedUserAdvice;


    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedController.class);

    @RequestMapping(path = "/studentProfile", method = RequestMethod.GET)
    public ModelAndView viewStudent(@ModelAttribute("profileStudentForm") final ProfileStudentForm form) {
        final ModelAndView mav = new ModelAndView("/perfil/student");
        LOGGER.debug("Showing student profile");
        return mav;
    }

    @RequestMapping(path = "/studentProfile", method = RequestMethod.POST)
    public ModelAndView viewStudent(@ModelAttribute("profileStudentForm")@Valid final ProfileStudentForm form, final BindingResult errors) throws IOException {
        if(errors.hasErrors())
            return viewStudent(form);
        userService.setUserImage(loggedUserAdvice.loggedUser().getUserId(),form.getProfileImage().getBytes());
        userService.updateUser(loggedUserAdvice.loggedUser().getUserId(),form.getName(), form.getSurname(), form.getEmail(), form.getPhone());
        LOGGER.debug("Updating student profile");
        return new ModelAndView("redirect:/student/" + loggedUserAdvice.loggedUser().getUserId());
    }

    @RequestMapping(value = "/professorProfile", method = RequestMethod.GET)
    public ModelAndView viewProfessor() {
        final ModelAndView mav = new ModelAndView("/perfil/professor");

        mav.addObject("loggedUser", loggedUserAdvice.loggedProfessor());
        //Paginacion
        final List<Contract> activeContracts = contractService.getContracts(new ContractFilter.Builder().professorId(loggedUserAdvice.loggedUser().getUserId()).status(Contract.ContractStatus.ACTIVE).build(),-1);
        final List<Contract> pausedContracts = contractService.getContracts(new ContractFilter.Builder().professorId(loggedUserAdvice.loggedUser().getUserId()).status(Contract.ContractStatus.PAUSED).build(),-1);
        mav.addObject("activeContracts", activeContracts);
        mav.addObject("pausedContracts",pausedContracts);
        LOGGER.debug("Showing professor profile");
        return mav;
    }

    @RequestMapping(value = "/professorProfile/{subjectId}",method = RequestMethod.POST)
    public ModelAndView viewProfessorDelete(@PathVariable Integer subjectId){
       contractService.dropContract(loggedUserAdvice.loggedUser().getUserId(),subjectId);
        LOGGER.debug("Professor profile droping subject {} ",subjectId);
        return viewProfessor();
    }

    @RequestMapping(value = "/professorProfileStatus/{contractId}/{status}",method = RequestMethod.POST)
    public ModelAndView viewProfessorUpdateStatus(@PathVariable Integer contractId,@PathVariable String status){
        contractService.changeContractStatus(contractId, Contract.ContractStatus.valueOf(status));
        LOGGER.debug("Professor profile updating contract  {} with status ",contractId,status);
        return new ModelAndView("redirect:/professorProfile");
    }

    @RequestMapping(value = "/editarProfessor", method = RequestMethod.GET)
    public ModelAndView editProfessor(@ModelAttribute("profileProfessorForm") final ProfileProfessorForm form) {
        final ModelAndView mav = new ModelAndView("/perfil/editProfessor");
        Professor prof = loggedUserAdvice.loggedProfessor();
        mav.addObject("loggedUser", prof);
        LOGGER.debug("Editing profesor form requested profesor {}",prof.getUserId());
        return mav;
    }

    @RequestMapping(path = "/editarProfesor", method = RequestMethod.POST)
    public ModelAndView editProfessor(@ModelAttribute("profileProfessorForm")@Valid final ProfileProfessorForm form, final BindingResult errors) throws IOException {
        if(errors.hasErrors())
            return editProfessor(form);
        Integer userId =loggedUserAdvice.loggedUser().getUserId();
        userService.setUserImage(userId,form.getProfileImage().getBytes());
        professorService.updateProfessor(userId,form.getName(), form.getSurname(), form.getEmail(),form.getSchedule(), form.getPhone(), form.getStudies(), form.getDescription());
        LOGGER.debug("Submiting edit professor form {}",userId);
        return new ModelAndView("redirect:/profesor/" + loggedUserAdvice.loggedUser().getUserId());
    }



    @RequestMapping("/user/image/{userId}")
    public void getUserImage(@PathVariable("userId") final Integer userId, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Getting user image: {}",userId);
        try {
            byte[] profileImage = userService.getUserImage(userId);
            response.setHeader("Content-Disposition", "inline;filename=\" image-" + userId + "\"");
            if (profileImage != null && profileImage.length > 0) {
                OutputStream out = response.getOutputStream();
                response.setContentLength(profileImage.length);
                IOUtils.copy(new ByteArrayInputStream(profileImage), out);
                out.flush();
                out.close();
            } else {
                response.sendRedirect(request.getContextPath() + "/resources/images/profilePhoto.jpeg");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    @RequestMapping(path = "/verify")
    public ModelAndView verify(){
        LOGGER.debug("Verify view requested");
        return new ModelAndView("/registracion/verify");
    }

    @RequestMapping(path = "/verify/{token}")
    public ModelAndView verifyUser(@PathVariable("token") String verificationToken) {
        LOGGER.debug("Verify view requested with token: {}",verificationToken);
        Optional<User> user = verificationService.verifyUser(verificationToken);
        user.ifPresent(value -> autoLogin(value.getEmail()));
        return new ModelAndView("redirect:/verify");
    }

    private void autoLogin(String email){
        LOGGER.debug("Auto-Login: {}",email);
        UserDetails userDetails = pawUserDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    @RequestMapping(path = "/verify/send", method = RequestMethod.POST)
    public ModelAndView reSendEmailVerify(){
        User user = loggedUserAdvice.loggedUser();
        LOGGER.debug("Re-sending email verification: {}",user.getUserId());
        emailService.sendVerifyEmail(user);
        return new ModelAndView("redirect:/");
    }


}
