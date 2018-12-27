package cn.joey.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther liujiji
 * @date 2018/12/20 17:13
 */
public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(ConditionConfig.class);
        Person person = configApplicationContext.getBean(Person.class);
        System.out.println(person);
        person.birth();
    }
}
