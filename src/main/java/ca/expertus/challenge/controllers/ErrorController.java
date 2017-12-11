package ca.expertus.challenge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public ModelAndView error403(HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/errors/403");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return model;
    }

    @GetMapping("/404")
    public ModelAndView error404(HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/errors/404");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return model;
    }

    @GetMapping("/410")
    public ModelAndView error410(HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/errors/410");
        response.setStatus(HttpServletResponse.SC_GONE);
        return model;
    }

    @GetMapping("/500")
    public ModelAndView error500(HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/errors/500");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return model;
    }
}
