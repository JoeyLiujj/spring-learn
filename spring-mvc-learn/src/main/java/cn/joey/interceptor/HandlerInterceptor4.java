package cn.joey.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptor4 extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("============ handler HandlerInterceptor4 preHandle");
		//此处返回false 执行handlerInteceptor3和handlerInterceptor4的preHandle，
		//还有handlerInterceptor3的afterCompletion方法，其他方法都不执行
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("============ handler HandlerInterceptor4 postHandler");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("============ handler HandlerInterceptor4 afterCompletion");
	}
}
