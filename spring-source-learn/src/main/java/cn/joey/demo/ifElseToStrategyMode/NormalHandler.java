package cn.joey.demo.ifElseToStrategyMode;

import cn.joey.demo.AbstractHandler;
import cn.joey.demo.HandlerType;
import cn.joey.demo.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @auther liujiji
 * @date 2019/4/22 10:36
 */
@Component
@HandlerType("1")
public class NormalHandler extends AbstractHandler {

    @Override
    public String handler(OrderDTO dto) {
        return "处理普通订单";
    }
}
