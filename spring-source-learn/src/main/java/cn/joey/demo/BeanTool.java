package cn.joey.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @auther liujiji
 * @date 2019/4/22 11:04
 */
@Component
public class BeanTool implements ApplicationContextAware{

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(context==null) {
            this.context = applicationContext;
        }
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }
}
