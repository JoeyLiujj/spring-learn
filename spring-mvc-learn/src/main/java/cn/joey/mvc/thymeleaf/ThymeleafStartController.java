package cn.joey.mvc.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thyme")
public class ThymeleafStartController {
    @RequestMapping("/hello")
    public String getMovie(ModelMap map){
        map.put("name","张三");
        map.put("query","查询");
        map.put("hello","World");
        return "views/hello";
    }
}
