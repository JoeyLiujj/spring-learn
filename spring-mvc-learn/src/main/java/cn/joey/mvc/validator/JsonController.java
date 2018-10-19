package cn.joey.mvc.validator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JsonController {

    @RequestMapping("/dataBind")
    public String requestJson(ItemsCustom itemsCustom, Model model,@RequestParam(defaultValue = "#{systemProperties['java.vm.version']}") String version){
        model.addAttribute("name", itemsCustom.getName());
        model.addAttribute("price", itemsCustom.getPrice());
        model.addAttribute("list",itemsCustom.getList());
        model.addAttribute("date",itemsCustom.getDate());
        model.addAttribute("item",itemsCustom.getItem());
        model.addAttribute("map",itemsCustom.getMap());
        model.addAttribute("version",version);
        return "binding.jsp";
    }
}
