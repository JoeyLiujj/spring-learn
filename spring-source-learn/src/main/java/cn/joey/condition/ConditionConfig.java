package cn.joey.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @auther liujiji
 * @date 2018/12/20 17:10
 */
@Configuration
@ComponentScan
public class ConditionConfig {

    @Bean
    @Conditional(ManCondition.class)
    public Man getMan(){
        return new Man();
    }

    @Bean
    @Conditional(WomanCondition.class)
    public Woman getWoman(){
        return new Woman();
    }
}
