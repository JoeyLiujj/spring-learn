package cn.joey.tomcat;

import javax.servlet.http.HttpServlet;

/**
 * @auther liujiji
 * @date 2019/6/18 10:37
 */
public class GPSecondServlet extends GPServlet {

    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is Second Serlvet");
    }
}
