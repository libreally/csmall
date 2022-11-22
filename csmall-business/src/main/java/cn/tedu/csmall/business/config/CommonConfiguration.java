package cn.tedu.csmall.business.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 当前类是配置spring扫描环境的配置类
 */

@Slf4j
@Configuration
@ComponentScan("cn.tedu.csmall.commons.exception")
public class CommonConfiguration {
    public CommonConfiguration(){
        log.debug("创建配置类：CommonConfiguration");
    }

}
