package cn.joey.spring.annotation;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2018/12/13 17:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface GPController {
    String value() default "";
}
