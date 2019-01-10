package cn.joey;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther liujiji
 * @date 2019/1/10 10:42
 */
public class UnitTestBase {
    public ClassPathXmlApplicationContext context;
    public String springXMLPath;
    public UnitTestBase(){}
    public UnitTestBase(String springXMLPath){
        this.springXMLPath = springXMLPath;
    }

    @Before
    public void before(){
        if(StringUtils.isEmpty(springXMLPath)){
            springXMLPath = "classpath*:spring-*.xml";
        }
        context = new ClassPathXmlApplicationContext(springXMLPath);
        context.start();
    }

    @After
    public void after(){
        context.destroy();
    }

    protected <T extends Object> T getBean(String beanID)  {
        return (T) context.getBean(beanID);
    }

    protected <T extends Object> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

}
