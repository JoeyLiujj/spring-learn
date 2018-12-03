package com.joey.mybatis.ver2;

import com.joey.mybatis.ver2.executor.GP2Executor;
import com.joey.mybatis.ver2.mapper.GP2TestMapper;
import com.joey.mybatis.ver2.session.GP2SqlSession;
import sun.security.util.Resources;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @auther liujiji
 * @date 2018/12/3 10:49
 */
public class TestDemo {
    public static void main(String[] args) {
        GP2Configuration configuration = new GP2Configuration();
        GP2Executor gp2Executor = configuration.newExecutor();
        GP2SqlSession sqlSession = new GP2SqlSession(configuration,gp2Executor);
        GP2TestMapper mapper = sqlSession.getMapper(GP2TestMapper.class);
        List<Object> query = mapper.selectByPrimarykey("RAA00020");
        System.out.println(query);
    }
}
