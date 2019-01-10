package cn.joey.rmi.rpc.client;

import cn.joey.rmi.rpc.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @auther liujiji
 * @date 2019/1/9 16:21
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port){
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setArgs(args);
        request.setMethod(method.getName());
        request.setClassName(method.getDeclaringClass().getName());


        Socket socket = new Socket(host,port);
        ObjectOutputStream objectOutputStream =new ObjectOutputStream(socket.getOutputStream());

        objectOutputStream.writeObject(request);
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object result = objectInputStream.readObject();

        objectOutputStream.close();
        objectInputStream.close();
        return result;
    }

}
