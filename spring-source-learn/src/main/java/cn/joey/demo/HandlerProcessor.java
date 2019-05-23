package cn.joey.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2019/4/22 11:14
 * 用于将标记了HandlerType注解的类，通过HandlerContext类加载进IoC容器中
 * 使用的时候我们直接去HandlerContext中去拿
 */
@Component
@SuppressWarnings("unchecked")
public class HandlerProcessor implements BeanFactoryPostProcessor {

    private static final String HANDLER_PACKAGE = "cn.joey.demo.ifElseToStrategyMode";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final Map<String,Class> handlerMap = new HashMap<>(3);
        ClassScanner.scan(HANDLER_PACKAGE,HandlerType.class).forEach(clazz->{
           String type = clazz.getAnnotation(HandlerType.class).value();
           handlerMap.put(type,clazz);
        });
        HandlerContext context = new HandlerContext(handlerMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(),context);
    }
}
