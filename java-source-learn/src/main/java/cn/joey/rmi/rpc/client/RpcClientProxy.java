package cn.joey.rmi.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @auther liujiji
 * @date 2019/1/9 15:49
 */
public class RpcClientProxy {
    public static <T> T clientProxy(final Class<T> interfaceCls,final String host,final int port){
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class[]{interfaceCls},new RemoteInvocationHandler(host,port));
    }
}
