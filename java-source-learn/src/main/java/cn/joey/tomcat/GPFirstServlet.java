package cn.joey.tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther liujiji
 * @date 2019/6/18 10:34
 */
public class GPFirstServlet extends GPServlet {

    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is First Serlvet");
    }
}
