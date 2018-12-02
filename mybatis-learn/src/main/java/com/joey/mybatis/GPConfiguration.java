package com.joey.mybatis;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2018/11/30 15:10
*/
public class GPConfiguration {
    public <T> T getMapper(Class<T> clazz,GPSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},
                new GPMapperProxy(sqlSession));
    }


    static class TestMaperXml{
        public static final String namespace="com.joey.mybatis.TestMapper";

        public static final Map<String,String> methodSql = new HashMap();

        static {
            methodSql.put("selectPrimaryKey","select * from test where id=%d");
        }

    }
}
