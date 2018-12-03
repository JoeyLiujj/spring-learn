package com.joey.mybatis.ver2.annotation;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2018/12/3 10:21
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GP2Select {
    String value();
}
