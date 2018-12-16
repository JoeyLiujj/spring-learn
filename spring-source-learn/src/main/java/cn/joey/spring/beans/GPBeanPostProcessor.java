package cn.joey.spring.beans;

/**
 * @auther liujiji
 * @date 2018/12/13 17:09
 */
public class GPBeanPostProcessor {
    public Object postProcessBeforeInitialization(Object instance, String beanName) {
        return instance;
    }

    public Object postProcessAfterInitialization(Object instance, String beanName) {
        return instance;
    }
}
