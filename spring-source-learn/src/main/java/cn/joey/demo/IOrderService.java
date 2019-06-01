package cn.joey.demo;

/**
 * @auther liujiji
 * @date 2019/4/22 10:32
 */
public interface IOrderService {

    String handle(OrderDTO dto) throws InstantiationException, IllegalAccessException;
}
