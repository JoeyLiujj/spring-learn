package cn.joey.importselector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @auther liujiji
 * @date 2019/5/23 10:10
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableHelloWorld.class.getName());
        Server.Type type =(Server.Type) annotationAttributes.get("type");

        String[] importselector=new String[0];

        switch(type) {
            case HTTP: importselector = new String[]{HttpServer.class.getName()};
            break;
            case FTP: importselector = new String[]{FtpServer.class.getName()};
        }

        return importselector;
    }
}
