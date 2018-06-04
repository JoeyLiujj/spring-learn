package cn.joey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class HandlerExceptionResolverDemo implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		System.out.println();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		return mv;
	}
}
