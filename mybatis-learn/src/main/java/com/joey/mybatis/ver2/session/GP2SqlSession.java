package com.joey.mybatis.ver2.session;

import com.joey.mybatis.ver2.GP2Configuration;
import com.joey.mybatis.ver2.executor.GP2Executor;

import java.sql.SQLException;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/3 9:47
 */
public class GP2SqlSession {
    private GP2Configuration configuration;
    private GP2Executor executor;

    public GP2SqlSession(GP2Configuration configuration, GP2Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class type) {
        return configuration.getMapper(this,type);
    }

    public <T> List<T> query(String sql,Object args) throws SQLException, ClassNotFoundException {
        return executor.query(sql,args);
    }
}
