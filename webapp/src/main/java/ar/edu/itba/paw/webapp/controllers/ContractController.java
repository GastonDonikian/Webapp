package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.services.ContractService;
import ar.edu.itba.paw.interfaces.services.SubjectService;
import ar.edu.itba.paw.models.Contract;
import ar.edu.itba.paw.models.ContractFilter;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.webapp.forms.ContractForm;
import ar.edu.itba.paw.webapp.forms.FilterContractForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class
ContractController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private LoggedUserAdvice loggedUserAdvice;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

    @RequestMapping(path = "/contracts",method = RequestMethod.GET)
    public ModelAndView contracts(@ModelAttribute("filterContractForm")final FilterContractForm filterContractForm){
        final ModelAndView mav = new ModelAndView("/contracts");
        Integer page = filterContractForm.getPage();

        if(page == null || page <= 0)
          page = 1;
        final ContractFilter filter = new ContractFilter.Builder().search(filterContractForm.getSearch()).categories(filterContractForm.getCategories()).levels(filterContractForm.getLevels()).localidades(filterContractForm.getLocalidades()).local(filterContractForm.getLocal()).remote(filterContractForm.getRemote()).orderBy(filterContractForm.getOrderBy()).build();

        mav.addObject("pages",contractService.getContractsCount(filter));
        mav.addObject("page",page);
        final List<Contract> contracts = contractService.getContracts(filter,page);
        mav.addObject("categories",contractService.getContractsCategories());
        mav.addObject("levels",contractService.getContractsLevels());
        mav.addObject("locations", contractService.getContractsLocations());
        mav.addObject("orderBy", ContractFilter.OrderBy.values());
        mav.addObject("contracts", contracts);

        return mav;
    }

    @RequestMapping(path = "/nuevoContrato/materia/{subjectId}",method = RequestMethod.GET)
    public ModelAndView teachSubject(@ModelAttribute("contractForm")final ContractForm form, @PathVariable(value = "subjectId")final Integer subjectId){
        final ModelAndView mav = new ModelAndView("nuevoContrato");
        mav.addObject("subject", subjectService.findById(subjectId));
        mav.addObject("locations", Professor.Location.values());
        LOGGER.debug("User is filling form to teach {} his user ID is {}",subjectId,loggedUserAdvice.loggedUser().getUserId());
        return mav;
    }

    @RequestMapping(path = "/nuevoContrato/materia/{subjectId}",method = RequestMethod.POST)
    public ModelAndView teachSubject(@ModelAttribute("contractForm")@Valid final ContractForm form, BindingResult errors, @PathVariable(value = "subjectId")final Integer subjectId){
        if(contractService.doesUserTeach(loggedUserAdvice.loggedUser().getUserId(),subjectId))
            return new ModelAndView("redirect:/professorProfile");
        if(errors.hasErrors())
            return teachSubject(form,subjectId);
        contractService.newContract(loggedUserAdvice.loggedUser().getUserId(), subjectId,form.getContractDescription(),form.isLocal(),form.isRemote(), Float.valueOf(form.getPrice()));
        LOGGER.debug("User sends form to teach {} his user ID is {}",subjectId,loggedUserAdvice.loggedUser().getUserId());
        return new ModelAndView("redirect:/professorProfile");
    }

    //todo borramos esto?
//    @RequestMapping(path = "/materia/{materia}", method = RequestMethod.GET)
//    public ModelAndView Subject(@PathVariable(value = "materia") final Long subjectId, @ModelAttribute("FilterContractForm") final FilterContractForm form, @RequestParam(value = "page",defaultValue = "1") Integer page) {
//        final ModelAndView mav = new ModelAndView("materia");
//        if(page <= 0)
//            page = 1;
//        mav.addObject("pages",contractService.getContractsCount(new ContractFilter.Builder().subjectId(subjectId).location(form.getLocalidades()).local(form.getLocal()).remote(form.getRemote()).orderBy(form.getOrderBy()).build()));
//        mav.addObject("page",page);
//        final Optional<Subject> subject = subjectService.findById(subjectId);
//        final List<Contract> contracts = contractService.getContracts(new ContractFilter.Builder().subjectId(subjectId).location(form.getLocalidades()).local(form.getLocal()).remote(form.getRemote()).orderBy(form.getOrderBy()).build(),page);
//        mav.addObject("locations", Professor.Location.values());
//        mav.addObject("orderBy", ContractFilter.OrderBy.values());
//        mav.addObject("contracts", contracts);
//        if(loggedUserAdvice.loggedUser() == null){
//            mav.addObject("teaches", false);
//        }
//        else
//        {
//            mav.addObject("teaches", contractService.doesUserTeach(loggedUserAdvice.loggedUser().getUserId(), subjectId));
//        }
//        mav.addObject("subject", subject);
//        LOGGER.debug("Subject {} showing subjects at page {}",subjectId,page);
//        return mav;
//    }

}
