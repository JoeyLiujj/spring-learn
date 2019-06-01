package cn.joey.mvc;

import cn.joey.mvc.jdbc.RiskExpressionRef;
import cn.joey.mvc.jdbc.RiskExpressionRefDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

public class CommonGenericTest {
    @Test
    public void testJdbc(){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("jdbcConfig-servlet.xml");
//        RiskExpressionRefDao jdbcEmpDao =(RiskExpressionRefDao) context.getBean("jdbcEmpDao");
//        List<RiskExpressionRef> list = jdbcEmpDao.findAll();
//        for (RiskExpressionRef obj : list) {
//            System.out.println(obj.getCalCode()+":::"+obj.getRiskCode());
//        }
//
//        RiskExpressionRefDao jdbcEmpDao2 =(RiskExpressionRefDao) context.getBean("jdbcRiskExpressionRefDao");
//        List<RiskExpressionRef> list2 = jdbcEmpDao.findAll();
//        for (RiskExpressionRef obj : list2) {
//            System.out.println(obj.getCalCode()+":::"+obj.getRiskCode());
//        }

    }
}
