package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.services.*;
import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.webapp.controllers.account.LoggedController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ProfessorController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private LoggedUserAdvice loggedUserAdvice;

    @Autowired
    private LoggedController loggedController;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private UserService userService;


    @Autowired
    private ProfessorService professorService;

    @Autowired
    private LessonService lessonService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorController.class);



    @RequestMapping("/profesor/{professorId}")
    public ModelAndView viewProfessor(@PathVariable(value = "professorId") final Integer professorId, @RequestParam(value = "page",defaultValue = "1",required = false) Integer page, @RequestParam(value = "rPage",defaultValue = "1",required = false) Integer rPage) {
        final ModelAndView mav = new ModelAndView("profesor");
        final Optional<User> user = userService.findById(professorId);
        if ( ! user.get().getIsProfessor()){
            return new ModelAndView("redirect:/student/" + user.get().getUserId());
        }

        if(loggedUserAdvice.loggedUser() != null){
                mav.addObject("isCurrentUser", Objects.equals(professorId, loggedUserAdvice.loggedUser().getUserId()));
        }
        else {
            mav.addObject("isCurrentUser", false);
        }


        final ReviewFilter filter = new ReviewFilter.Builder().professor(professorId).build();
        mav.addObject("reviews", reviewService.getReviews(filter,rPage));
        mav.addObject("totalReviews", reviewService.getReviewsCount(filter));

        if(rPage <= 0)
            rPage = 1;
        mav.addObject("rPages", reviewService.getReviewsPages(filter));
        mav.addObject("rPage",rPage);

        if(page <= 0)
            page = 1;
        LOGGER.debug("Accesing Professor profile {} with page number: {}",professorId,page);

        final ContractFilter contractFilter = new ContractFilter.Builder().professorId(professorId).build();
        mav.addObject("pages",contractService.getContractsCount(contractFilter));
        mav.addObject("page",page);
        final Optional<Professor> profesor = professorService.findById(professorId);
        if(!profesor.isPresent() || !profesor.get().isProfessor() )
            return new ModelAndView("redirect:/student/" + professorId);
        final List<Contract> contracts = contractService.getContracts(contractFilter,page);
        mav.addObject("totalContracts", contractService.totalContracts(contractFilter));
        mav.addObject("prof", profesor.get());
        mav.addObject("contracts", contracts);
        return mav;
    }

    @RequestMapping(value = "/profesor/{professorId}/{subjectId}",method = RequestMethod.GET)
    public ModelAndView viewProfessor(@PathVariable(value = "professorId") final Integer professorId,@PathVariable(value = "subjectId")final Integer subjectId,@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "rPage",defaultValue = "1",required = false) Integer rPage){
        final ModelAndView mav = new ModelAndView("profesorWithSubject");
        final Optional<User> user = userService.findById(professorId);
        if ( ! user.get().getIsProfessor()){
            return new ModelAndView("redirect:/student/" + user.get().getUserId());
        }

        if(loggedUserAdvice.loggedUser() != null){
            mav.addObject("isCurrentUser", Objects.equals(professorId, loggedUserAdvice.loggedUser().getUserId()));
        }
        else {
            mav.addObject("isCurrentUser", false);
        }

        final ReviewFilter filter = new ReviewFilter.Builder().professor(professorId).subject(subjectId).build();
        mav.addObject("reviews", reviewService.getReviews(filter,rPage));

        mav.addObject("reviews", reviewService.getReviews(filter,rPage));
        mav.addObject("totalReviews", reviewService.getReviewsCount(filter));
        mav.addObject("allReviews", reviewService.getReviewsCount( new ReviewFilter.Builder().professor(professorId).build()));

        if(rPage <= 0)
            rPage = 1;
        mav.addObject("rPages", reviewService.getReviewsPages(filter));
        mav.addObject("rPage",rPage);


        if(page <= 0)
            page = 1;

        final ContractFilter contractFilter = new ContractFilter.Builder().professorId(professorId).build();
        mav.addObject("pages",contractService.getContractsCount(contractFilter));
        mav.addObject("page",page);
        final Optional<Professor> profesor = professorService.findById(professorId);

        mav.addObject("totalContracts", contractService.totalContracts(contractFilter));
        final List<Contract> contracts = contractService.getContracts(contractFilter,page);
        mav.addObject("prof", profesor.get());
        mav.addObject("contracts", contracts);
        final List<Contract> mainContract = contractService.getContracts(new ContractFilter.Builder().professorId(professorId).subjectId(subjectId).build(),1);
        if(mainContract.isEmpty())
            return viewProfessor(professorId,1,1);
        mav.addObject("mainContract",mainContract.get(0));
        LOGGER.debug("Accesing Professor profile {} with main contract: {} with page number: {}",professorId,mainContract.get(0).getId(),page);
        return mav;
    }


    @RequestMapping(path = "/profesor/{professorId}/{subjectId}",method = RequestMethod.POST)
    public ModelAndView contactProfessor(@PathVariable(value = "professorId")final Integer professorId,@PathVariable(value = "subjectId")final Integer subjectId){
        final Optional<User> auxUser = userService.findById(professorId);
        if ( ! auxUser.get().getIsProfessor()){
            return new ModelAndView("redirect:/student/" + auxUser.get().getUserId());
        }
        List<Contract> mainContract = contractService.getContracts(new ContractFilter.Builder().professorId(professorId).subjectId(subjectId).build(),1);
        if(mainContract.isEmpty())
            return new ModelAndView("redirect:/error500");
        lessonService.newLesson(subjectId,professorId,loggedUserAdvice.loggedUser().getUserId(), mainContract.get(0).getPrice(), Lesson.LessonStatus.PENDING_APPROVAL,false,true);
        LOGGER.debug("Requesting new lesson from contract {} by user: {} ",mainContract.get(0).getId(),loggedUserAdvice.loggedUser().getUserId());
        return new ModelAndView("redirect:/myLessons");
    }

    @RequestMapping("/student/{studentId}")
    public ModelAndView viewStudent(@PathVariable(value = "studentId") final Integer studentId) {
        final ModelAndView mav = new ModelAndView("alumno");
        final Optional<User> user = userService.findById(studentId);
        if (user.get().getIsProfessor()){
            return new ModelAndView("redirect:/profesor/" + user.get().getUserId());
        }
        if(loggedUserAdvice.loggedUser() != null){
            mav.addObject("isCurrentUser", Objects.equals(studentId, loggedUserAdvice.loggedUser().getUserId()));
        }
        else {
            mav.addObject("isCurrentUser", false);
        }
        mav.addObject("user", user.get());

        //LOGGER.debug("Accesing Student profile {} with page number: {}",professorId,page);

        return mav;
    }

}
