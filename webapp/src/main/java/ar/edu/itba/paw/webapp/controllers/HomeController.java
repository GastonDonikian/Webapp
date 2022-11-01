package ar.edu.itba.paw.webapp.controllers;



import ar.edu.itba.paw.models.*;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home", "/welcome"})
    public ModelAndView home() {
        final ModelAndView mav = new ModelAndView("home");
        mav.addObject("categories", Subject.Categories.values());
        return mav;
    }
}