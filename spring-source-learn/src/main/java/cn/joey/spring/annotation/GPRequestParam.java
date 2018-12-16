package cn.joey.spring.annotation;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2018/12/13 17:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface GPRequestParam {
    String value() default "";
    boolean required() default true;
}
