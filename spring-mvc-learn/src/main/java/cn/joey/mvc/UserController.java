package cn.joey.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class UserController {

    public static String getMessage(HttpServletRequest request, String key,String locale) {
        Locale currentLocale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("messages",currentLocale);
        return bundle.getString(key);
    }

    @RequestMapping("/i18u")
    public ModelAndView getUsers(HttpServletRequest request, @RequestParam(required = false) String locale){
        ModelAndView mv =  new ModelAndView();
        //从message中获取文字
        String showUserInfo = getMessage(request, "showUserInfo",locale);
        String userManage = getMessage(request, "userManage",locale);
        String userName = getMessage(request, "userName",locale);
        String age = getMessage(request, "age",locale);
        String photoName = getMessage(request, "photoName",locale);
        String photo = getMessage(request, "photo",locale);
        String addUser = getMessage(request, "addUser",locale);

        mv.addObject("showUserInfo", showUserInfo);
        mv.addObject("userManage", userManage);
        mv.addObject("userName", userName);
        mv.addObject("age", age);
        mv.addObject("photoName", photoName);
        mv.addObject("photo", photo);
        mv.addObject("addUser", addUser);
        mv.setViewName("i18u");
        return mv;
    }
}
