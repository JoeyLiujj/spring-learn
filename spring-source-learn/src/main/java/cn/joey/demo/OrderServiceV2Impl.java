package cn.joey.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther liujiji
 * @date 2019/4/22 10:34
 */

@Service
public class OrderServiceV2Impl implements IOrderService {

    @Autowired
    private HandlerContext handlerContext;
    @Override
    public String handle(OrderDTO dto) throws InstantiationException, IllegalAccessException {
        AbstractHandler handler = handlerContext.getInstance(dto.getType());
        return handler.handler(dto);
    }
}
