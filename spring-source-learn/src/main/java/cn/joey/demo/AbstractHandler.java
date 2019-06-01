package cn.joey.demo;

import org.springframework.stereotype.Component;

/**
 * @auther liujiji
 * @date 2019/4/22 10:37
 */
@Component
public abstract class AbstractHandler {
    abstract public String handler(OrderDTO dto);
}
