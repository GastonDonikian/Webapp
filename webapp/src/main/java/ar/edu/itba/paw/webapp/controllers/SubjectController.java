package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.services.ContractService;
import ar.edu.itba.paw.interfaces.services.SubjectService;
import ar.edu.itba.paw.models.Contract;
import ar.edu.itba.paw.models.ContractFilter;
import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.SubjectFilter;
import ar.edu.itba.paw.webapp.forms.FilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private LoggedUserAdvice loggedUserAdvice;

    @RequestMapping(path = "/materias", method = RequestMethod.GET)
    public ModelAndView allSubjects(@ModelAttribute("FilterForm") final FilterForm form, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        final ModelAndView mav = new ModelAndView("materias");

        if (page <= 0)
            page = 1;
        SubjectFilter subjectFilter = new SubjectFilter.Builder().search(form.getSearch()).categories(form.getCategories()).levels(form.getLevels()).build();
        final List<Subject> subjects = subjectService.getUntaughtSubjects(loggedUserAdvice.loggedUser().getUserId(),subjectFilter, page);

        //Paginacion
        mav.addObject("pages", subjectService.getUntaughtSubjectsCount(loggedUserAdvice.loggedUser().getUserId(),subjectFilter));
        mav.addObject("page", page);


        //Objetos de negocio
        mav.addObject("categories", subjectService.getSubjectCategories());
        mav.addObject("levels", subjectService.getSubjectLevels());
        mav.addObject("materias", subjects);
        return mav;
    }
}
