package cn.tedu.csmall.stock.webapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 如果当前项目是dubbo调用中的生产者,必须添加@EnableDubbo注解
// 添加之后,在服务启动时,当前项目的所有服务才能正确的注册到Nacos
@EnableDubbo
public class CsmallStockWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsmallStockWebapiApplication.class, args);
    }

}
