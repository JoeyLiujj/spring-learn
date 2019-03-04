package cn.joey.rmi.rpc.server;

import cn.joey.rmi.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @auther liujiji
 * @date 2019/1/9 15:46
 */
public class ProcessHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessHandler(Socket socket, Object service){
        this.service =service;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream =null;
        ObjectOutputStream objectOutputStream=null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest =(RpcRequest) objectInputStream.readObject();
            Object invoke = invoke(rpcRequest);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(invoke);
            objectOutputStream.flush();

        } catch (Exception e) {
            throw new RuntimeException("连接失败");
        } finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object[] args = request.getArgs();
        Class<?>[] types = new Class[args.length];
        for(int i=0;i<types.length;i++){
            types[i] = args[i].getClass();
        }

        Method method = service.getClass().getMethod(request.getMethod(),types);
        return method.invoke(service,args);
    }
}
