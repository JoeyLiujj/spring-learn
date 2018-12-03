package com.joey.mybatis.ver2;

import com.joey.mybatis.ver2.GP2Configuration;
import com.joey.mybatis.ver2.session.GP2SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auther liujiji
 * @date 2018/12/3 10:04
 */
public class GP2MethodProxy implements InvocationHandler {

    private GP2SqlSession sqlSession;

    public GP2MethodProxy(GP2SqlSession sqlSession, Class type) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(GP2Configuration.namespace)){
            String sql = GP2Configuration.registerMap.get(method.getName());
            return sqlSession.query(sql,args);
        }
        return method.invoke(proxy,args);
    }
}
