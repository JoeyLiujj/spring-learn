package cn.joey.condition;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Target;

/**
 * @auther liujiji
 * @date 2018/12/20 17:10
 */
//@Configuration
//@ConditionalOnBean(value = {DefaultListableBeanFactory.class})
//@ConditionalOnProperty(prefix= "spring",name="test",value = "false")
//@ComponentScan
public class ConditionConfig {

//    @Bean
//    @Conditional(ManCondition.class)
    public Man getMan(){
        return new Man();
    }

//    @Bean
//    @Conditional(WomanCondition.class)
    public Woman getWoman(){
        return new Woman();
    }
}
