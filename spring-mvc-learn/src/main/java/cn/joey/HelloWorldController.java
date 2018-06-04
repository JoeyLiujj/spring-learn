package cn.joey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloWorldController implements Controller {
	// 1、收集参数、验证参数
	// 2、绑定参数到命令对象
	// 3、将命令对象传入业务对象进行业务处理
	// 4、选择下一个页面
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hello World!");
		mv.addObject("username", username);
		mv.setViewName("hello");
		System.out.println("是否在处理之中");
		return mv;
	}
	
}