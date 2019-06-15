package cn.joey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther liujiji
 * @date 2019/6/2 14:39
 */
@Configuration
public class FormatterAutoConfiguration {

    @Bean
    public Formatter defaultFormatter(){
        return new DefaultFormatter();
    }
}
