package cn.joey.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

public @interface CustomAnnotation {
    String value() default "";
}
