package cn.joey;

import cn.joey.aop.annotationconfig.PuchaseService;
import cn.joey.aop.introduction.*;
import cn.joey.aop.xmlconfig.UserService;
import cn.joey.condition.ConditionConfig;
import cn.joey.demo.IOrderService;
import cn.joey.demo.OrderDTO;
import cn.joey.demo.OrderServiceV2Impl;
import cn.joey.jdbc.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import javax.servlet.ServletContainerInitializer;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

@Slf4j
public class CommonGenericTest {

    @Test
    public void testSpEL() {
        log.info("can used");
    }

    @Test
    public void testMethodAdvice() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ac.getBean("userService");
        userService.add();
    }

    @Test
    public void testAspectJExpression() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotationAspectJ.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("introductionAdvisor.xml");
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

    @Test
    public void test(){
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(resource);
    }


    @Test
    public void testHashCode(){
        System.out.println(DefaultListableBeanFactory.class.hashCode());
    }

    @Test
    public void testAnnotation(){
        SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory();
        try {
            MetadataReader metadataReader = factory.getMetadataReader("cn.joey.aop.annotationconfig.PuchaseService");
            log.info(metadataReader.toString());
            StandardAnnotationMetadata sam = new StandardAnnotationMetadata(ConditionConfig.class);
            Set<String> annotationTypes = sam.getAnnotationTypes();
            for(String annotation:annotationTypes){
                log.info(annotation);
                Set<String> metaAnnotationTypes = sam.getMetaAnnotationTypes(annotation);
                if(metaAnnotationTypes!=null){
                    for(String metaAnnotationType:metaAnnotationTypes){
                        log.info(metaAnnotationType);
                    }
                }
                Map<String,Object> annotationAttributes = sam.getAnnotationAttributes(annotation);
                log.info(annotationAttributes.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAnnotationContext(){
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext("cn.joey");
        PuchaseService puchaseService = (PuchaseService)context.getBean("puchaseService");
        puchaseService.purchaseProduct("",0,"");
        log.info(puchaseService.toString());
    }

    @Test
    public void testFastJson(){
        String value="   a,   ,b,c  d          ";
        String[] values = value.split("\\s*[,]+\\s*");
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        System.out.println(values.length);
    }


    @Test
    public void testCustomHandlers(){
        ApplicationContext context = new ClassPathXmlApplicationContext("myTarg.xml");

        cn.joey.spring.handler.User user = (cn.joey.spring.handler.User)context.getBean("user");
        log.info(user.getUserName()+"----------"+user.getEmail());

    }


    @Test
    public void testIfElse() throws IllegalAccessException, InstantiationException {
        ApplicationContext context=new AnnotationConfigApplicationContext("cn.joey.demo");

        OrderServiceV2Impl v2 =(OrderServiceV2Impl) context.getBean("orderServiceV2Impl");
        OrderDTO dto = new OrderDTO();
        dto.setCode("1234");
        dto.setPrice(new BigDecimal(1223));
        dto.setType("3");
        String handle = v2.handle(dto);
        log.info(handle);
    }



    @Test
    public void testIsAssignableFromMethod(){
        String[] a = new String[2];
        String b="111";

        OrderServiceV2Impl impl = new OrderServiceV2Impl();
        System.out.println(a.getClass().isAssignableFrom(Object.class));
        System.out.println(b.getClass().isAssignableFrom(String.class));
        System.out.println(impl.getClass().isAssignableFrom(IOrderService.class));
        System.out.println(IOrderService.class.isAssignableFrom(OrderServiceV2Impl.class));
        System.out.println(Object.class.isAssignableFrom(IOrderService.class));
    }

    @Test
    public void testStreamApi(){
        List<String> list = new ArrayList<>();

        list.add("abc");
        list.add("acd");
        list.add("bce");
        list.add("led");

        Stream<String> stream = list.stream();
        stream.filter(name -> name.equals("abc")).forEach(a -> log.info(a));

        Stream.of("one","two","three","four").filter(e -> e.length()>3)
                .peek(e -> log.info("Filtered value:"+e))
                .map(String::toUpperCase)
                .peek(e -> log.info("Mapped value:"+e))
                .collect(Collectors.toList());


        Optional.ofNullable("zhangsan").map(String::length).orElse(-1);


        Stream.iterate(0,n->n+3).forEach(x -> System.out.print(x+" "));
    }

    @Test
    public void testCompareTo(){
        List<cn.joey.socket.User> lists = new ArrayList<>();

        cn.joey.socket.User user1 = new cn.joey.socket.User("zhangsan","zhangsan1");
        cn.joey.socket.User user3 = new cn.joey.socket.User("wangmazi","wangmazi1");
        cn.joey.socket.User user2 = new cn.joey.socket.User("lisi","lisi1");

        lists.add(user3);
        lists.add(user1);
        lists.add(user2);
        lists.stream().sorted(cn.joey.socket.User::compareTo).forEach((user)-> log.info(user.toString()));


        List<String> strs = Arrays.asList("好,好,学","习,天,天","向,上");

        strs.stream().map((str) -> str.split(",")).forEach((str) -> System.out.println(str));

    }

    @Test
    public void testSPI() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader loader = ClassUtils.getDefaultClassLoader();
        Enumeration<URL> resources = systemClassLoader.getResources("META-INF/services/javax.servlet.ServletContainerInitializer");
        List<ServletContainerInitializer> containerInitializers = new ArrayList<>();
        while(resources.hasMoreElements()){
            URL url = resources.nextElement();
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String className;
            while((className = reader.readLine())!=null){
                log.info(url.toString()+" "+className);
                Class<?> clazz = loader.loadClass(className);
                ServletContainerInitializer instance =(ServletContainerInitializer) clazz.newInstance();
                containerInitializers.add(instance);
            }
            log.info("--------------------------------");
        }
        containerInitializers.forEach((initilizer) -> log.info(initilizer.toString()));

    }

}
