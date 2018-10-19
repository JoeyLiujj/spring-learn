package cn.joey.mvc.jdbc;

import cn.joey.mvc.dao.RiskExpressionRefDAO;
import cn.joey.mvc.entity.RiskExpressionRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyBatisSpringMvcController {

    @Autowired
    private RiskExpressionRefDAO dao;

    @RequestMapping("/list")
    public String execute(Model model){
        List<RiskExpressionRef> list=dao.findAll();
        for(RiskExpressionRef obj:list){
            model.addAttribute("emps",list);
        }
        return "jsp/emp_list";
    }
}
