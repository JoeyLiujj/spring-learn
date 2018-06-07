package cn.joey;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

@RestController
public class ContentTypeDemo {
	
	@RequestMapping(value="/testRESTController",method=RequestMethod.GET)
	public String showForm() {
		return "This is a test String";
	}
	@RequestMapping(value="/ContentType",method=RequestMethod.POST,headers="Content-Type=application/x-www-form-urlencoded")
	public String request1(HttpServletRequest request) throws IOException{
		String contentType=request.getContentType();
		System.out.println("========ContentType:"+contentType);
		String characterEncoding=request.getCharacterEncoding();
		System.out.println("========CharacterEncoding:"+characterEncoding);
		
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		return "success";
	}

	@RequestMapping(value = "/request/ContentType", method = RequestMethod.POST)
	public String request2(HttpServletRequest request) throws IOException {
		// ①表示请求的内容区数据为json数据
		InputStream is = request.getInputStream();
		byte bytes[] = new byte[request.getContentLength()];
		is.read(bytes);
		// ②得到请求中的内容区数据（以CharacterEncoding解码）
		// 此处得到数据后你可以通过如json-lib转换为其他对象
		String jsonStr = new String(bytes, request.getCharacterEncoding());
		System.out.println("json data：" + jsonStr);
		String header = request.getHeader("host");
		System.out.println(header);
		return "success";
	}
	@RequestMapping("/response/ContentType")
	public void request3(HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write("<font style='color:red'>hello</font>");
	}
	@RequestMapping(value="/response/ContentType1",headers="Accept=application/json")
	public void response(HttpServletResponse response) throws IOException{
//		response.setContentType("application/xml;charset=utf-8");
//		String jsonData="{\"username\":\"zhangsan\",\"password\":\"1234qwer\"}";
//		response.getWriter().write(jsonData);  
		String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";  
	    xmlData += "<user><username>zhang</username><password>123</password></user>"; 
		response.getWriter().write(xmlData);  
	}
	@RequestMapping(value="/response/ContentType2",headers="Accept=application/xml")
	public void response1(HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";  
	    xmlData += "<user><username>zhang</username><password>123</password></user>";
		response.getWriter().write(xmlData);  
	}

//	@PathVariable 注解将会绑定 URL 占位符到入参
	@RequestMapping(value = "/paramTest/{id}")
	//可将指定参数名称的值传递给name
	public void testParam(HttpServletResponse response,@RequestParam("age") String name,@PathVariable String id) throws IOException{
//		int i=1/0;
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("是否会打印url路径的ID："+id);
		response.getWriter().write(name);
	}

	//类中的方法出现异常时，会执行此方法，参数为异常Throwable的子类型
	@ExceptionHandler({java.lang.IllegalStateException.class,java.lang.ArithmeticException.class})
	public void testExceptionHandler(HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("出现异常时，执行此方法");
	}
	//@ControllerAdvice
	//使一个Contoller成为全局的异常处理类，
	// 类中用@ExceptionHandler方法注解的方法可以处理所有Controller发生的异常
	//	@PathVariable
	//　绑定URL占位符到入参
}
