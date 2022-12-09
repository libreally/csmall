package cn.tedu.csmall.business.rabbit;

import cn.tedu.csmall.commons.pojo.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    int i=1;
    @Scheduled(fixedRate = 10000)
    public void sendMessage(){
        Order order=new Order();
        order.setId(i++);
        order.setUserId("UU100");
        order.setCommodityCode("PC100");
        order.setCount(1+ RandomUtils.nextInt(10));
        order.setMoney(20+RandomUtils.nextInt(200));

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.BUS_EX,
                RabbitMQConfig.BUS_ROUT,
                order);
        log.info("发送消息:{}",order);
    }




}
