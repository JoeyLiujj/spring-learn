package cn.joey.mvc.validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterSimpleFormController {
    @RequestMapping("/bindError")
    public String testBindException(Items items){
        BindException errors = new BindException(items,getCommandName(items));
        errors.reject("username","username.not.empty");
        ModelAndView mv = new ModelAndView();
        mv.addAllObjects(errors.getModel());
        System.out.println(mv.getModel());
        return "error.jsp";
    }
    //如果使用委托方式，命令对象名称只能是command
    protected String getCommandName(Object command) {
        //命令对象的名字 默认command
        return "items";
    }
}
