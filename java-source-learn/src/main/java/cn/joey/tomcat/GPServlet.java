package cn.joey.tomcat;

import javax.servlet.http.HttpServlet;

/**
 * @auther liujiji
 * @date 2019/6/18 14:02
 */
public abstract class GPServlet {

    public void service(GPRequest request,GPResponse response) throws Exception{

        //由service方法来决定，是调用doGet或者调用doPost
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else{
            doPost(request, response);
        }

    }

    public abstract void doGet(GPRequest request,GPResponse response) throws Exception;

    public abstract void doPost(GPRequest request,GPResponse response) throws Exception;
}