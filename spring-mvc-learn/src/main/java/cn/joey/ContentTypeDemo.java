package cn.joey;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentTypeDemo {
	
	@RequestMapping(value="/ContentType",method=RequestMethod.GET)
	public String showForm() throws IOException{
		return "consumesproduces/Content-Type";
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
}
