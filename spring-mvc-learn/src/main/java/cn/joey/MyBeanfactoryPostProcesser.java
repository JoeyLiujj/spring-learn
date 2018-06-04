package cn.joey;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanfactoryPostProcesser implements BeanFactoryPostProcessor{
	//容器处理器，在applicationContext应用上下文加载之后执行 BeanFactory的初始化
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		System.out.println("程序同意Spring所做的BeanFactory的初始化");
	}

}
