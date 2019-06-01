package cn.joey.importselector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @auther liujiji
 * @date 2019/5/23 11:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
    Server.Type type();
}
