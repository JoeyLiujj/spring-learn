package cn.joey.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspectJ {
    @Pointcut("execution(* com.*.*Log(String,..) throws Error)")
    public void print() {

    }

//    @AfterReturning("within(cn.joey.aop.aspectj.PuchaseService) && @annotation(fl)")
//    @AfterReturning("execution(* com.*.*(..))")
    @AfterReturning(pointcut = "print()")
    public void addSuccessLog(JoinPoint jp) {
        Object[] params = jp.getArgs();
        //获取目标方法体参数
        //获取目标类名
        String className = jp.getTarget().getClass().toString();
        //获取目标方法签名
        String signature = jp.getSignature().toString();
        //获取注解值
        String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));

//        String desc = fl.value();
        //把调用的细信息写到日常记录信息里面去
        System.out.println("调用方法返回：addSuccessLog()---" + className + "---->");
    }

    //    @AfterThrowing(pointcut = "within(cn.joey.aop.aspectj.PuchaseService) && @annotation(fl)",throwing = "e")
    @AfterThrowing("execution(* com.*.*(..))")
    public void addExceptionLog(JoinPoint jp) {
    }

}
