package cn.joey.socket;

import java.io.*;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            Socket socket=null;
            ObjectInputStream is=null;
            ObjectOutputStream os = null;

            try {
                socket = new Socket("localhost",10000);

                os = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                User user = new User("user_"+i,"password_"+i);
                os.writeObject(user);
                os.flush();

                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

                Object obj = is.readObject();
                if (obj != null) {
                    user = (User) obj;
                    System.out.println("user:"+user.getName()+" password:"+user.getPassword());
                }

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
    }
}
