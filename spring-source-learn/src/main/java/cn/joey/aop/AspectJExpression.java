package cn.joey.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

@Aspect
@Component
public class AspectJExpression {

//    @Pointcut("execution(* cn.joey.aop.UserService.*(..)) throws java.io.IOException")
//    @Pointcut("within(cn.joey.aop.UserService+)")  //within中使用的表达式必须是类型全限定名，不支持通配符
//    @Pointcut("this(cn.joey.aop.UserService+)")   //this中使用的表达式必须是类型全限定名，不支持通配符
//    @Pointcut("target(cn.joey.aop.UserService)")  //target中使用的表达式必须是类型全限定名，不支持通配符
//    @Pointcut("args(java.lang.Integer,..)") // 匹配方法参数是指定类型的方法，且后面可以有多个参数..
                                            //参数类型列表中的参数必须是类型全限定名，通配符不支持
//    @Pointcut("@within(java.lang.Deprecated)") //匹配所以持有指定注解类型内的方法；注解类型也必须是全限定类型
                                               // 必须是在目标对象上声明这个注解
    @Pointcut("@annotation(cn.joey.aop.CustomAnnotation)") //匹配指定方法上有给定注解的所有方法。
    public void definiteExpression(){

    }

    @Before("definiteExpression()")
    public void beforeMethod(JoinPoint jp){
        System.out.println("前置拦截方法，成功拦截"+jp.getSignature().getName());
    }

    @After("definiteExpression()")
    public void afterMethod(JoinPoint jp){
        System.out.println("后置拦截方法，成功拦截"+jp.getSignature().getName());
    }

}
