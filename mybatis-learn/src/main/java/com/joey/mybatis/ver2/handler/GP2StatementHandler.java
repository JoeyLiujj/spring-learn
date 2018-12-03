package com.joey.mybatis.ver2.handler;

import java.sql.SQLException;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/3 14:00
 */
public interface GP2StatementHandler {
    <T> List<T> prepareStatement(String sql, Object args) throws SQLException, ClassNotFoundException;
}
