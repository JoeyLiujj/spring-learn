package cn.joey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class I18nController {
    @RequestMapping(value = "/hello")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("greeting","Welcome Here");
        return modelAndView;
    }
}
