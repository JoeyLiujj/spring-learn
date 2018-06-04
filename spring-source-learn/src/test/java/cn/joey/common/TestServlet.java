package cn.joey.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	private ThreadLocal<PrintWriter> output = new ThreadLocal<PrintWriter>();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username;
		response.setContentType("text/html; charset=gb2312");
		username = request.getParameter("username");
		output.set(response.getWriter());
		try {

			Thread.sleep(5000); // 为了突出并发问题，在这设置一个延时
		} catch (InterruptedException e) {
		}
		this.output.get().println("用户名:" + username + "<BR>");
	}
}
