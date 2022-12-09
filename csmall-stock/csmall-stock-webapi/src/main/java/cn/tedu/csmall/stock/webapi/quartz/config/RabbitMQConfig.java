package cn.tedu.csmall.stock.webapi.quartz.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 当前配置类配置RabbitMQ中 交换机\路由Key和队列的关系
// 因为它们的关系需要保存到Spring容器中才能生效,所以需要这个配置类
@Configuration
public class RabbitMQConfig {
    // 将业务中需要的所有交换机\路由Key\队列的名称都声明为常量
    public static final String STOCK_EX="stock_ex";
    public static final String STOCK_ROUT="stock_rout";
    public static final String STOCK_QUEUE="stock_queue";

    // 绑定关系中,交换机和队列是实际对象,直接实例化保存到Spring容器
    @Bean
    public DirectExchange stockDirectExchange(){
        return new DirectExchange(STOCK_EX);
    }
    @Bean
    public Queue stockQueue(){
        return new Queue(STOCK_QUEUE);
    }
    // 路由Key是关系对象,保存方式特殊
    @Bean
    public Binding stockBinding(){
        return BindingBuilder.bind(stockQueue())
                            .to(stockDirectExchange()).with(STOCK_ROUT);
    }

}
