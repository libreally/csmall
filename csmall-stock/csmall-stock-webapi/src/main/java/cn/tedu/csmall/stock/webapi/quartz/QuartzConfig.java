package cn.tedu.csmall.stock.webapi.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// QuartzConfig类来绑定调用的方法和触发的关系
// 这个触发实际上会由Spring容器中的Scheduler对象调度
// 凡是Spring容器内容的配置都需要添加@Configuration注解
@Configuration
public class QuartzConfig {
    // Quartz任务调度生效条件是将JobDetail和Trigger对象保存到Spring容器中
    // JobDetail对象封装任务内容,Trigger对象封装触发时机

    // 利用@Bean注解,将JobDetail和Trigger对象保存到Spring容器中
    @Bean
    public JobDetail showTime(){
        // JobDetail对象中配置时要将欲触发的Job接口实现类配置在框架指定的方法中
        // JobBuilder.newJob方法是可以创建JobDetail对象的,方法参数就是Job接口实现类的反射即可
        return JobBuilder.newJob(QuartzJob.class)
                // 需要给当前任务起名,不要和其他任务重名
                .withIdentity("dateTime")
                // 默认情况下JobDetail生成后,如果没有触发器绑定会自动移除
                // 设置storeDurably()方法后,JobDetail生成后即使没有被绑定,也不会被移除了
                .storeDurably()
                .build();
    }

    // 下面开始编写触发器的配置,目标是绑定上面JobDetail,实现在指定时间触发
    @Bean
    public Trigger showTimeTrigger(){
        // 声明Cron表达式,定义触发时间
        CronScheduleBuilder cron=
                CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        return TriggerBuilder.newTrigger()
                // 绑定要运行的JobDetail对象
                .forJob(showTime())
                // 当前触发器也要起名字,名字也不要重复
                .withIdentity("dateTimeTrigger")
                // 绑定cron表达式
                .withSchedule(cron)
                .build();
    }

    @Bean
    public JobDetail addStock(){
        return JobBuilder.newJob(QuartAddStock.class)
                .withIdentity("addStock")
                .storeDurably()
                .build();
    }
    //@Bean
    public Trigger addStockTrigger(){
        CronScheduleBuilder cron=
                CronScheduleBuilder.cronSchedule("0 0/2 * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(addStock())
                .withIdentity("addStockTrigger")
                .withSchedule(cron)
                .build();
    }






}
