package com.joey.mybatis;

import java.util.List;

public class Entry {
    public static void main(String[] args) {
        GPSqlSession sqlSession = new GPSqlSession(new GPConfiguration(),new GPSimpleExecutor());
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        List<Object> objects = mapper.selectPrimaryKey();
        System.out.println(objects);
    }
}
