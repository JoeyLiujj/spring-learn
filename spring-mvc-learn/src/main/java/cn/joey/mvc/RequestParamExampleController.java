package cn.joey.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class RequestParamExampleController {

    @RequestMapping(method= RequestMethod.GET)
    public String sayHello(ModelMap model){
        model.addAttribute("greeting","Hello World from Spring 4 MVC");
        return "welcome.jsp";
    }
    @RequestMapping(value = "/user")
    public String userInfo(Model model, @RequestParam(value = "name",defaultValue = "Guest") String name){
        model.addAttribute("name",name);
        if ("admin".equals(name)) {
            model.addAttribute("email","admin@163.com");
        }else{
            model.addAttribute("email","Not set");
        }
        return "userInfo.jsp";
    }

}
