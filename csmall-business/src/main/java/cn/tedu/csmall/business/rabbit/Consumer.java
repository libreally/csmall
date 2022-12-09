package cn.tedu.csmall.business.rabbit;

import cn.tedu.csmall.commons.pojo.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMQConfig.BUS_QUEUE)
@Slf4j
public class Consumer {

    @RabbitHandler
    public void process(Order order){
        log.info("接收到消息:{}", order);
    }



}
