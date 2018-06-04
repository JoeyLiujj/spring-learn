package cn.joey;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloWorldTest {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/classes/springmvc-servlet.xml");
		HelloWorld model = (HelloWorld)context.getBean("HelloWorld");
		System.out.println(model.getMessage());
	}
}
