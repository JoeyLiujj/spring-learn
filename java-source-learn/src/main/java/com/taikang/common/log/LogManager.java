package com.taikang.common.log;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taikang.common.safeUtil.SafeHelper;

public class LogManager implements Filter{
	
	private final Logger logger = LoggerFactory.getLogger(LogManager.class);

	private String urlInfo = "本次请求的地址为：";
	private String pramInfo = "传入的参数为：";
	private String runTimeInfo = "运行时间为：";
	private String exceptionInfo = "异常是：";
	private int maxTime = 2000;//单位是毫秒 --超过两面进行警告处理
	private String warnInfo = "本次请求超过2秒，请进行优化";
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		//获取请求之前的系统时间
		long startTime = System.currentTimeMillis();
	
		//获取请求的参数
		StringBuffer paramList = new StringBuffer();
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			String parameter = request.getParameter(paraName);
			if("password".equals(paraName) || "rpassword".equals(paraName) || paraName.contains("phone") || paraName.contains("Phone")){
				String encParameter = SafeHelper.enc(parameter);
				paramList.append(paraName +":"+encParameter+",");
			}else {
				paramList.append(paraName +":"+parameter+",");
			}
		}  
		if(paramList.length() ==0){
			paramList.append("为空，");
		}
		String url = request.getRequestURI();
		if(url !=null){
			//设置需要打印日志的请求
			if(url.endsWith(".interface") || url.endsWith(".do") || url.endsWith(".ajax")){
				try{
					arg2.doFilter(arg0, arg1);
					long endTime = System.currentTimeMillis();
					logger.info(urlInfo+request.getRequestURI()
							+pramInfo+paramList
							+runTimeInfo+(endTime-startTime)+"毫秒");
					//获取请求执行所用时间
					if(endTime-startTime >= maxTime){
						logger.warn(urlInfo+request.getRequestURI()
							+pramInfo+paramList
							+runTimeInfo+(endTime-startTime)+"毫秒,"
							+warnInfo);
					}
				}catch(Exception e){
						
					logger.error("本次请求地址为："+request.getRequestURI()
							+"\n\r传入的参数为："+paramList
							+"请求异常，错误信息为"+e.toString(),e);
					throw e;
				}
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}else{
			arg2.doFilter(arg0, arg1);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
