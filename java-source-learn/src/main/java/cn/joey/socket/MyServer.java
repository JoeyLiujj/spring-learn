package cn.joey.socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class MyServer {
    private final static Logger logger = Logger.getLogger(MyServer.class.getName());
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =new ServerSocket(10000);

        while(true){
            Socket socket = serverSocket.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream is = null;
                ObjectOutputStream os = null;

                try {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    os = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    Object obj = is.readObject();
                    User user = (User)obj;
                    System.out.println("user:"+user.getName()+" password:"+user.getPassword());

                    user.setName(user.getName()+"_new");
                    user.setPassword(user.getPassword()+"_new");

                    os.writeObject(user);
                    os.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
