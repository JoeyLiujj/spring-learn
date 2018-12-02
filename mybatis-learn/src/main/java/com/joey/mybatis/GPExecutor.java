package com.joey.mybatis;

/**
 * @auther liujiji
 * @date 2018/11/30 15:09
 */
public interface GPExecutor {

    <T> T query(String statement, String parameters);

}
