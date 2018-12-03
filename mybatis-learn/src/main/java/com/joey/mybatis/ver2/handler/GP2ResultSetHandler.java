package com.joey.mybatis.ver2.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/3 14:04
 */
public interface GP2ResultSetHandler {

    <T> List<T> query(ResultSet rs) throws SQLException;
}
