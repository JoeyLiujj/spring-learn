package cn.joey.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther liujiji
 * @date 2018/12/20 17:13
 */
@Slf4j
public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(ConditionConfig.class);
        Person person = configApplicationContext.getBean(Person.class);
        log.info(person.toString());
        person.birth();
    }
}
