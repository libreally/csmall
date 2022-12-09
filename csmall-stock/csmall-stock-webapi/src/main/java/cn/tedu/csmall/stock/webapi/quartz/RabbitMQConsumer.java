package cn.tedu.csmall.stock.webapi.quartz;

import cn.tedu.csmall.commons.pojo.stock.model.Stock;
import cn.tedu.csmall.stock.webapi.quartz.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// Spring连接RabbitMQ的依赖中提供的资源,需要接收消息的类保存到Spring容器中才能使用
@Component
// 和Kafka不同,RabbitMQ监听器注解要求写在类上
@RabbitListener(queues = RabbitMQConfig.STOCK_QUEUE)
@Slf4j
public class RabbitMQConsumer {

    // 类上添加了监听器注解,但是不能指定接收消息后要运行的方法
    // 使用@RabbitHandler注解标记,我们接收到消息后要运行的方法
    // 每个类只允许一个方法被这个注解标记
    // 注解下面编写方法,参数比Kafka简单
    @RabbitHandler
    public void process(Stock stock){
        // Stock就是发送到RabbitMQ的消息,直接使用即可
        log.info("接收到消息:{}",stock);
    }

}
