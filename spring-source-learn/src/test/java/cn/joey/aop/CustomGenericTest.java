package cn.joey.aop;

import cn.joey.aop.annotationconfig.PuchaseService;
import cn.joey.aop.introduction.Auto;
import cn.joey.aop.introduction.Intelligent;
import cn.joey.aop.introduction.Seller;
import cn.joey.aop.introduction.Waiter;
import cn.joey.aop.xmlconfig.UserService;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class CustomGenericTest {

    @Test
    public void testSpEL() {
        System.out.println("can used");
    }

    @Test
    public void testMethodAdvice() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ac.getBean("userService");
        userService.update(1);
        userService.add();
    }

    @Test
    public void testAspectJExpression() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("testAspectJExpression.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.update(1);
        userService.add();
    }

    @Test
    public void testForLoopReturn() {
        for (int i = 0; i < 40; i++) {
            if (i == 10) return;
            System.out.println(i);
        }
    }

    @Test
    public void testLoadSpringHandlers() {
        try {
            String resourceName = "META-INF/spring.handlers";
            ClassLoader classLoaderToUse = null;
            if (classLoaderToUse == null) {
                classLoaderToUse = ClassUtils.getDefaultClassLoader();
            }
            Enumeration<URL> urls = (classLoaderToUse != null ? classLoaderToUse.getResources(resourceName) :
                    ClassLoader.getSystemResources(resourceName));
            Properties props = new Properties();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                URLConnection con = url.openConnection();
                ResourceUtils.useCachesIfNecessary(con);
                InputStream is = con.getInputStream();
                try {
                    props.load(is);
                } finally {
                    is.close();
                }
            }
            Enumeration<Object> prop = props.elements();
            while (prop.hasMoreElements()) {
                System.out.println(prop.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // can do anything
        }
    }

    @Test
    public void testSpelFunction() {
        String expression = "#conttype == '1' and ((#stateflag == '0' and #uwflag != '1' and #uwflag != '8' and #uwflag != 'a') or (#appflag == '1' and #stateflag == '1'))";
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("contType", "1");
        context.setVariable("stateFlag", "0");
        context.setVariable("uwFlag", "1");
        context.setVariable("appFlag", "100");
        Expression exp = parser.parseExpression(expression);
        Object value = exp.getValue(context);
        System.out.println(value);
    }

    @Test
    public void testIntroduction() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Auto car = (Auto) context.getBean("myCar");
        System.out.println(car.getClass());
        car.driving();
        Intelligent intelligentCar = (Intelligent) car;
        intelligentCar.selfDriving();

    }

    @Test
    public void testAspectj() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("logAspect.xml");
        PuchaseService purchaseService = (PuchaseService) context.getBean("purchaseService");
        purchaseService.purchaseProduct("电风扇", 98, "日用品");
    }

    @Test
    public void testAspectjIntroduction() {
        ApplicationContext context = new ClassPathXmlApplicationContext("testAspectJIntroduction.xml");
        Waiter waiter = (Waiter) context.getBean("waiter");
        waiter.greetTo("Java3y");
        waiter.serveTo("Java3y");

        //通过引介切面可以将waiter服务员实现了Seller接口，可以强制转换
        Seller seller = (Seller) waiter;
        seller.sell("水军", "Java3y");
    }

    @Before
    public void before() {
        System.out.println("测试之前执行");
    }


    @Test
    public void test(){
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(resource);
    }


}
