package cn.joey.spring.annotation;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2018/12/13 17:53
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GPService {
    String value() default "";
}
