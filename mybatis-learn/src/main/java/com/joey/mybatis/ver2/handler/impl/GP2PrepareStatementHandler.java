package com.joey.mybatis.ver2.handler.impl;

import com.joey.mybatis.ver2.GP2Configuration;
import com.joey.mybatis.ver2.handler.GP2ResultSetHandler;
import com.joey.mybatis.ver2.handler.GP2StatementHandler;

import java.sql.*;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/3 14:05
 */
public class GP2PrepareStatementHandler implements GP2StatementHandler {

    private static GP2ResultSetHandler resultSetHandler;

    static {
        resultSetHandler = new GP2DefaultResultSetHandler();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.130.130.12:3306/ics_cbs_calengine", "root@db_dev_cloud0_967", "picch1234");
        return connection;
    }

    private String getRealSql(String sql, Object args) {
        Object[] objects = (Object[]) args;
        int i = sql.indexOf("%");
        String substring = sql.substring(0, i);
        substring = substring+"'"+objects[0]+"'";
        return substring;
    }

    @Override
    public <T> List<T> prepareStatement(String sql, Object args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String realSql = getRealSql(sql,args);
        PreparedStatement preparedStatement = connection.prepareStatement(realSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetHandler.query(resultSet);
    }
}
