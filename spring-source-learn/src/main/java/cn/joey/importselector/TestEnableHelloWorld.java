package cn.joey.importselector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

/**
 * @auther liujiji
 * @date 2019/5/23 10:16
 */


@EnableHelloWorld(type = Server.Type.HTTP)
@Configuration
public class TestEnableHelloWorld {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TestEnableHelloWorld.class);

        context.refresh();

        Server server =(Server) context.getBean(HttpServer.class.getName());

        server.start();

        server.end();
    }
}
