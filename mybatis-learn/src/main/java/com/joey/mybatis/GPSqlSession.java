package com.joey.mybatis;

/**
 * @auther liujiji
 * @date 2018/11/30 15:05
 */
public class GPSqlSession  {
    private GPConfiguration configuration;
    private GPExecutor executor;

    public GPSqlSession(GPConfiguration configuration, GPExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }
}
