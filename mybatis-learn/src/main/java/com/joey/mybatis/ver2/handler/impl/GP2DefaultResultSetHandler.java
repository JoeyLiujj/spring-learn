package com.joey.mybatis.ver2.handler.impl;

import com.joey.mybatis.ver2.entity.GP2Test;
import com.joey.mybatis.ver2.handler.GP2ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/3 14:26
 */
public class GP2DefaultResultSetHandler implements GP2ResultSetHandler {
    @Override
    public <T> List<T> query(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        while(rs.next()){
            GP2Test test = new GP2Test();
            test.setCalCode(rs.getString("calcode"));
            test.setAccType(rs.getString("acctype"));
            list.add(test);
        }
        return list;
    }
}
