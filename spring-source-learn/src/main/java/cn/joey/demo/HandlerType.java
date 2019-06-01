package cn.joey.demo;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2019/4/22 10:46
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    String value() default "";
}
