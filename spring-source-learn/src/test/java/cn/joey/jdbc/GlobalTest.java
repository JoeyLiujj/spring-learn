package cn.joey.jdbc;

import cn.joey.jdbc.TemplateSupport;
import cn.joey.jdbc.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/21 14:11
 */
public class GlobalTest {

    @Test
    public void testAnnotation(){
        ApplicationContext  context = new ClassPathXmlApplicationContext("spring-db.xml");
        TemplateSupport templateTest =(TemplateSupport) context.getBean("simple");
        List<Object> select = templateTest.select(User.class);
        System.out.println(Arrays.toString(select.toArray()));
    }
}
