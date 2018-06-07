package cn.joey.aop;

import cn.joey.aop.annotationconfig.PuchaseService;
import cn.joey.aop.introduction.Auto;
import cn.joey.aop.introduction.Intelligent;
import cn.joey.aop.introduction.Seller;
import cn.joey.aop.introduction.Waiter;
import cn.joey.util.SortAlgorithm;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

public class CustomGenericTest {

    @Test
    public void testSpEL() {
        System.out.println("can used");
    }

    @Test
    public void testSortAlgorithm() {
        int arr[] = {3, 534, 2, 3, 43, 54, 2123, 43};
        SortAlgorithm.bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "->");
        }
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
//        String expression = "1 == 1  and ((0 == 0 and 2 != 1 and 2 != 8 and 2 != 'a') or ('1' == '1' and '1' == '1')) and 0 == 0 and 30 >= 0  and 30 <= 40";
        String expression = "(1+2) ";
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
//        context.setVariable("a","1");
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
}
