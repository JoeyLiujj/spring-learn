package cn.joey.mvc.annotation;

import org.springframework.stereotype.Repository;

@Repository
public @interface MyBatisRepository {
    String value() default "";
}
