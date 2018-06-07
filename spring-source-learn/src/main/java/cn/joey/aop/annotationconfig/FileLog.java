package cn.joey.aop.annotationconfig;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileLog {
    String value() default  "日志记录开始";
}
