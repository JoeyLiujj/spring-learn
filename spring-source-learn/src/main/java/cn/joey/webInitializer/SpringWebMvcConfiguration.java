package cn.joey.webInitializer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = SpringWebMvcConfiguration.class)
public class SpringWebMvcConfiguration {
}
