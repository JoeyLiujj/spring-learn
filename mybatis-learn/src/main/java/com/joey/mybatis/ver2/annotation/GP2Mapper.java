package com.joey.mybatis.ver2.annotation;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2018/12/3 10:21
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GP2Mapper {
    String value() default "";
}
