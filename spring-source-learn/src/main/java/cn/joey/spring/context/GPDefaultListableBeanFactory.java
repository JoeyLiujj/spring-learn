package cn.joey.spring.context;

import cn.joey.spring.beans.GPBeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2018/12/13 14:02
 */
public class GPDefaultListableBeanFactory extends GPAbstractApplicationContext{

    protected Map<String, GPBeanDefinition> beanDefinitionMap = new HashMap<String, GPBeanDefinition>();

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void refreshBeanFactory() {

    }
}
