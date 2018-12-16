package cn.joey.spring.annotation;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2018/12/13 17:58
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GPAutoWired {
    String value() default "";
}
