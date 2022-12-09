package cn.tedu.csmall.stock.webapi.quartz;

import cn.tedu.csmall.commons.pojo.stock.model.Stock;
import cn.tedu.csmall.stock.webapi.quartz.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QuartzJob implements Job {

    // 装配能够向RabbitMQ发送消息的对象
    // 这个对象也是添加好依赖和配置之后,在springBoot启动时自动向容器中保存的
    @Autowired
    private RabbitTemplate rabbitTemplate;
    static int i=1;
    // 这个方法就是当前job要定时执行的任务代码
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        // 一个简单的任务演示,输出当前系统时间,使用sout或log皆可
        log.info("-------------------" + LocalDateTime.now() + "--------------------");
        // 实例化Stock对象用于发送
        Stock stock=new Stock();
        stock.setId(i++);
        stock.setCommodityCode("PC100");
        stock.setReduceCount(1+ RandomUtils.nextInt(20));
        // 下面开始发送消息的RabbitMQ
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.STOCK_EX,
                RabbitMQConfig.STOCK_ROUT,
                stock);
        log.info("发送消息完成:{}",stock);


    }
}
