package cn.joey.spring.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @auther liujiji
 * @date 2019/4/1 9:50
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport{
    @Override
    public void init() {
        registerBeanDefinitionParser("user",new UserDefinitionParser());
    }
}
