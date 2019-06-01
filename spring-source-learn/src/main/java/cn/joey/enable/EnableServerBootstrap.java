package cn.joey.enable;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(EnableServerBootstrap.class);
        annotationConfigApplicationContext.refresh();

        Server server=annotationConfigApplicationContext.getBean(Server.class);

        server.start();
    }
}
