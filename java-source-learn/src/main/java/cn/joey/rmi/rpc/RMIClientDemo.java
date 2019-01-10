package cn.joey.rmi.rpc;

import java.rmi.Naming;

public class RMIClientDemo {

    public static void main(String[] args) throws Exception{
        GPHello helloService=
                (GPHello) Naming.lookup("rmi://127.0.0.1/Hello");
        // HelloServiceImpl实例(HelloServiceImpl_stub)
        // RegistryImpl_stub
        System.out.println(helloService.sayHello("Mic"));
    }
}
