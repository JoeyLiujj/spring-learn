package cn.joey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Arrays;


/**
 * ① @SpringBootApplication 注解包含了@ComponentScan @Configuration @EnableAutoConfiguration注解
 * ② @Configuration 等同于spring的传统XML配置文件；使用Java代码可以检查类型安全
 * ③ @ComponentScan 组件扫描，可以自动装配和发现一些Bean,如@Bean注解的bean
 * ④ @EnableAutoConfiguration 根据添加的jar包自动配置你的Spring应用
 * ⑤ @RestController 是@Controller和@ResponseBody注解的合集，REST风格
 * ⑥ @Autowired 自动导入
 * ⑦ @JsonBackReference解决嵌套外链问题。
 * ⑧ @RepositoryRestResource配合spring-boot-starter-data-rest使用
 * ⑨ @ImportSource 注解加载xml配置文件
 * ⑩ @Import 用来导入其他配置类。
 * ⑪ @EnableAsync //注解表示开启 @Async注解的解析；作用就是将串行化的任务给并行化了
 * ⑫ @EnableScheduling //注解表示开启@Scheduling注解的解析
 * ⑬⑭⑮⑯⑰⑱⑲⑳
 */
@SpringBootApplication(exclude = {FreeMarkerAutoConfiguration.class, MongoAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@Slf4j
public class SpringBootLearnRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnRunApplication.class, args);
    }

    /**
     * 很关键：默认情况下 TaskScheduler 的 poolSize = 1
     * private volatile int poolSize=1; 这导致了多个任务的情况下容易出现竞争情况
     * （多个任务的情况下，如果第一个任务没执行完毕，后续的任务将会进入等待状态）
     * @return 线程池
     */
//    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }
//    @Bean
//    @Order(value = 1)
    public CommandLineRunner commandLineRunner1(ApplicationContext context){
        return args ->{
            log.info("Let's inspect the beans provided by Spring Boot!");
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for(String beanName:beanNames) {
                log.info(beanName);
            }
        };
    }

}
