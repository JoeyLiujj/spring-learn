package cn.joey;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class, MongoAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,RabbitAutoConfiguration.class,
        RedisAutoConfiguration.class})
public class ExitCodeGeneratorBootstrap {

    @Bean
    public ExitCodeGenerator exitCodeGenerator(){
        System.out.println("ExitCodeGenerator Bean 创建...");
        return ()->{
            System.out.println("执行退出码(88)生成...");
            return 88;
        };
    }

    public static void main(String[] args) {
//        new SpringApplicationBuilder(ExitCodeGeneratorBootstrap.class)
//                .web(false)
//                .run(args)
//                .close();

        int exitCode = SpringApplication.exit(new SpringApplicationBuilder(ExitCodeGeneratorBootstrap.class).web(false).run(args));

        System.exit(exitCode);
    }
}
