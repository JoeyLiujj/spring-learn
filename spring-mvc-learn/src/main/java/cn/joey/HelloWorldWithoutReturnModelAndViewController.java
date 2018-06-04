package cn.joey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

public class HelloWorldWithoutReturnModelAndViewController extends
		AbstractController implements LastModified {

	private long lastModified;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.getWriter().write("Hello World！!");
		response.setCharacterEncoding("gb2312");
		return null;
	}

	// Spring 判断是否过期的代码
	// this.notModified = (ifModifiedSince >= (lastModifiedTimestamp / 1000 *
	// 1000));
	// 即请求的“If-Modified-Since” 大于等于当前的getLastModified方法的时间戳，则认为没有修改
	@Override
	public long getLastModified(HttpServletRequest paramHttpServletRequest) {
		System.out.println(lastModified);
		System.out.println(paramHttpServletRequest
				.getHeader("If-Modified-Since"));
		if (lastModified == 0l) {
			lastModified = System.currentTimeMillis();
		}
		return lastModified;
	}

}
