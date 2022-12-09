package cn.tedu.csmall.cart.webapi.kafka;

import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import com.google.gson.Gson;
import org.apache.commons.lang.math.RandomUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 启动时要将这个类实例化保存到Spring容器,才能执行周期运行的效果
@Component
public class Producer {

    // Spring-Kafka框架会自动将能够操作发送消息的对象注入到Spring容器
    // 我们直接使用@Autowired自动装配即可
    // KafkaTemplate<[话题类型],[消息类型]>
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    //定义话题常量
    public static final String TOPIC_KEY="myCart";
    int i=1;
    // 编写实现每隔8秒(8000毫秒)运行一次的方法来发送消息到Kafka
    @Scheduled(fixedRate = 8000)
    public void sendMessage(){
        // 实例化要发送的对象并赋值
        Cart cart=new Cart();
        cart.setId(i++);
        cart.setCommodityCode("PC100");
        cart.setUserId("UU100");
        cart.setPrice(10+ RandomUtils.nextInt(90));
        cart.setCount(1+ RandomUtils.nextInt(10));
        //将cart转换为json
        Gson gson = new Gson();
        String json = gson.toJson(cart);
        System.out.println("json = " + json);
        kafkaTemplate.send(TOPIC_KEY,json);
    }




}
