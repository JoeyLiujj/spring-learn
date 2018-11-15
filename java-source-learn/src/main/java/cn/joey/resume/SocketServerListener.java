package cn.joey.resume;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SocketServerListener extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        for(int i=0;i<3;i++) {
            if("instart".equals(FinalVariables.IS_START_SERVER)){
                open();
                break;
            }
        }
    }
    public void open(){
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    FileUploadServer fileUploadServer=new FileUploadServer(FinalVariables.SERVER_PORT);
                    fileUploadServer.load();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },3000);

    }
}
