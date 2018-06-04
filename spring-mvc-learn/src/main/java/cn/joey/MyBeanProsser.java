package cn.joey;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanProsser implements BeanPostProcessor {
	// Bean后处理，在容器处理器之后调用，进行一些Bean初始化的附加功能
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanname)
			throws BeansException {
		System.out.println("Bean后处理器在初始化之后对" + beanname + "进行处理");
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanname)
			throws BeansException {
		System.out.println("Bean后处理器在初始化之前对" + beanname + "进行处理");
		return bean;
	}

}
