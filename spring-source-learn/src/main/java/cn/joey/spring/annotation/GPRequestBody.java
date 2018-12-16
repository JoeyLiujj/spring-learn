package cn.joey.spring.annotation;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2018/12/13 17:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface GPRequestBody {
}
