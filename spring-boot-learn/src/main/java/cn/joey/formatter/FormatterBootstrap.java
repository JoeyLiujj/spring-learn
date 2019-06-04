package cn.joey.formatter;

import cn.joey.Formatter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2019/6/2 14:52
 */
@EnableAutoConfiguration()
public class FormatterBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FormatterBootstrap.class)
                .web(WebApplicationType.NONE).run(args);

        Map<String, Object> data = new HashMap<>();
        data.put("name","小马哥");
        Formatter formatter = context.getBean(Formatter.class);

        System.out.printf("formatter.format(data): %s\n",formatter.format(data));

        context.close();

    }
}
