package cn.joey.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rabbitmq")
public class RabbitMQController {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public void defaultMessage(){
        this.rabbitTemplate.convertAndSend("myDefaultMessage","我发送的消息");
    }
}
