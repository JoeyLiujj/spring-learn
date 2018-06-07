package cn.joey.aop.annotationconfig;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

public @interface CustomAnnotation {
    String value() default "";
}
