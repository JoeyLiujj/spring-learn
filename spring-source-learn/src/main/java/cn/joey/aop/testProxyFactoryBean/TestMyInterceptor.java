package cn.joey.aop.testProxyFactoryBean;

import cn.joey.aop.xmlconfig.UserService;
import cn.joey.aop.xmlconfig.UserServiceImpl;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @auther liujiji
 * @date 2018/12/14 15:25
 */
public class TestMyInterceptor {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aopcontext.xml");
        Object proxybean = context.getBean("proxybean");
        UserService im = (UserService)proxybean;
        im.add();
    }
}
