package cn.joey.controller;

import cn.joey.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class HelloController {
    @RequestMapping("/hello")
    public String HelloTest(){
        return "hello";
    }

    @RequestMapping("/success")
    public String testUser(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        User user = new User();
        user.setUsername("张三");
        user.setPassword("来，就来了");
        map.put("user",user);
        return "success";
    }

    @GetMapping("/getMapping")
    @ResponseBody
    public String userGetMappingAnnotation(){
        return "测试@GetMapping注解";
    }
}
