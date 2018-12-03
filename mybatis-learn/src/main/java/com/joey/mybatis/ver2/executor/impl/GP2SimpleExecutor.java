package com.joey.mybatis.ver2.executor.impl;

import com.joey.mybatis.ver2.GP2Configuration;
import com.joey.mybatis.ver2.executor.GP2Executor;
import com.joey.mybatis.ver2.handler.GP2StatementHandler;
import com.joey.mybatis.ver2.handler.impl.GP2PrepareStatementHandler;
import org.apache.ibatis.plugin.InterceptorChain;
import org.apache.ibatis.plugin.Plugin;

import java.sql.SQLException;

/**
 * @auther liujiji
 * @date 2018/12/3 10:40
 */
public class GP2SimpleExecutor implements GP2Executor {
    protected GP2Configuration configuration;

    public GP2SimpleExecutor(GP2Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public <T> T query(String sql, Object args) throws SQLException, ClassNotFoundException {
         GP2StatementHandler statementHandler = (GP2StatementHandler)configuration.newStatementHandler();
//        GP2StatementHandler statementHandler = new GP2PrepareStatementHandler();
        return (T) statementHandler.prepareStatement(sql, args);
    }

}
