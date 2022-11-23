package cn.tedu.csmall.cart.webapi.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@MapperScan("cn.tedu.csmall.cart.webapi.mapper")
public class MybatisConfiguration {
    public MybatisConfiguration(){
        log.debug("创建配置类：MybatisConfiguration");
    }
}
