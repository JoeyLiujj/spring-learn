package com.joey.mybatis.ver2;

/**
 * @auther liujiji
 * @date 2018/12/3 9:50
 */
public class GP2MethodData<T> {
    private String sql;
    private String methodName;

    public GP2MethodData(String methodName,String sql) {
        this.sql = sql;
        this.methodName = methodName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
