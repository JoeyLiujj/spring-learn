package cn.joey.spring.beans;

/**
 * @auther liujiji
 * @date 2018/12/13 17:08
 */
public class GPBeanDefinition {
    private String beanClassName;
    private String factoryBeanName;

    private boolean lazyInit =false;
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }
}
