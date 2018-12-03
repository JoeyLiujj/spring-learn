package com.joey.mybatis;

import sun.misc.ProxyGenerator;

import java.lang.reflect.Proxy;

/**
 * @auther liujiji
 * @date 2018/12/3 9:23
 */
public class TestProxy {

    public static void main(String[] args) {
        Class<?> proxyClass = Proxy.getProxyClass(GPMapperProxy.class.getClassLoader(), new Class[]{TestMapper.class});
//        ProxyGenerator.ge
    }
}
