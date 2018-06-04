package cn.joey;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class MyDemoClient {
	public static void main(String[] args) {
		String url = "http://localhost:8080/smvc/request/ContentType";
		try {
			ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url),HttpMethod.POST);
			request.getHeaders().set("Content-Type", "application/xml;charset=gbk");
			String jsonData="{\"username\":\"zhangsan\",\"password\":\"1234qwer\"}";
			request.getBody().write(jsonData.getBytes("gbk"));
			ClientHttpResponse response = request.execute();
			System.out.println(response.getStatusCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			xmlRequest();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private static void xmlRequest() throws IOException, URISyntaxException{
		String url="http://localhost:8080/smvc/response/ContentType";
		ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.POST);
		//设置客户端可接受的媒体类型（即）
		request.getHeaders().set("Accept", "application/xml");
		ClientHttpResponse response = request.execute();
		Charset charset = response.getHeaders().getContentType().getCharset();
		InputStream is = response.getBody();
		byte bytes[]=new byte[(int)response.getHeaders().getContentLength()];
		is.read(bytes);
		String xmlData = new String(bytes,charset);
		System.out.println(charset +",xml data:"+xmlData);
	}
}
