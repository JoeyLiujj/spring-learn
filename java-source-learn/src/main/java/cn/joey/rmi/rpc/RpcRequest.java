package cn.joey.rmi.rpc;

import java.io.Serializable;

/**
 * @auther liujiji
 * @date 2019/1/9 16:22
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 4042323002216763918L;
    private String method;
    private Object[] args;
    private String className;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
