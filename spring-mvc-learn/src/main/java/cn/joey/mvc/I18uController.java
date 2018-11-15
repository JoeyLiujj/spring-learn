package cn.joey.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class I18uController {
    @RequestMapping(value = "/hello")
    public ModelAndView welcome(){

        ModelAndView modelAndView = new ModelAndView("welcome.jsp");
        modelAndView.addObject("greeting","Welcome Here");
        return modelAndView;
    }
}
