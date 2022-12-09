package cn.tedu.csmall.cart.webapi.kafka;


import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 接收kafka发送的消息
 */
@Component
public class Consumer {
    @KafkaListener(topics = Producer.TOPIC_KEY)
    public void received(ConsumerRecord<String,String> record){
        String json = record.value();
        Gson gson = new Gson();
        Cart cart = gson.fromJson(json,Cart.class);
        System.out.println("cart = " + cart);
    }
}
