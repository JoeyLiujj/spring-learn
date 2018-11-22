package cn.joey.bio;

import lombok.extern.slf4j.Slf4j;

import java.net.Socket;

@Slf4j
public class Client {
    private static int DEFAULT_SERVER_PORT = 7777;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT,expression);
    }

    public static void send(int port, String expression) {
        Socket socket = null;

    }
}
