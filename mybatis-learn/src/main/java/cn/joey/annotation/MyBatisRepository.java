package cn.joey.annotation;

import org.springframework.stereotype.Repository;

@Repository
public @interface MyBatisRepository {
    String value() default "";
}
