package com.joey.mybatis.ver2.executor;

import java.sql.SQLException;

/**
 * @auther liujiji
 * @date 2018/12/3 9:47
 */
public interface GP2Executor {
    <T> T query(String sql,Object args) throws SQLException, ClassNotFoundException;
}
