package cn.joey.spring.beans;

import cn.joey.spring.aop.GPAopConfig;
import cn.joey.spring.aop.GPAopProxy;
import cn.joey.spring.core.GPFactoryBean;

/**
 * @auther liujiji
 * @date 2018/12/13 14:08
 */
public class GPBeanWrapper extends GPFactoryBean {

    private GPAopProxy aopProxy = new GPAopProxy();

    private Object wrapperInstance;

    private Object originalInstance;
    private GPAopConfig aopConfig;
    private GPBeanPostProcessor postProcessor;

    public GPBeanWrapper(Object instance){
        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originalInstance = instance;
    }
    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public void setOriginalInstance(Object originalInstance) {
        this.originalInstance = originalInstance;
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }

    public void setAopConfig(GPAopConfig aopConfig) {
        this.aopConfig = aopConfig;
    }

    public void setPostProcessor(GPBeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }
}
