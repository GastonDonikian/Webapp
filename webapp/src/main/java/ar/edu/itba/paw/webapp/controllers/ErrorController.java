package ar.edu.itba.paw.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);


    @RequestMapping("/error404")
    public ModelAndView handleError404(@RequestHeader(value = "referer",required = false)final String referer) {
        LOGGER.debug("Llegue a error404 viniendo de: {}", referer); //con markers + prof header referrer para 404
        return new ModelAndView("errors/error404");
    }

    @RequestMapping("/error500")
    public ModelAndView handleError500(@RequestHeader(value = "referer",required = false)final String referer) {
        LOGGER.debug("Llegue a error500 viniendo de: {}", referer);
        return new ModelAndView("errors/error500");
        }

    @RequestMapping("/error403")
    public ModelAndView handleError403(@RequestHeader(value = "referer",required = false)final String referer) {
        LOGGER.debug("Llegue a error403 viniendo de: {}", referer);
        return new ModelAndView("errors/error403");
    }

}
