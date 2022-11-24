package cn.tedu.csmall.business.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusinessServiceImpl implements IBusinessService {
    //Dubbo调用order模块
    //business是单纯的消费者 不需要@DubboService
    @DubboReference
    private IOrderService dubboOrderService;


    public BusinessServiceImpl(){
        log.debug("创建业务实现类：BusinessServiceImpl");
    }

    //设置为TM（分布式事务的起点），最终效果是当前方法生效后，
    // 所有的远程调用操作数据库的功能，都在同一个事物中
    //也就是这些数据库要么执行要么不执行
    @Override
    @GlobalTransactional
    public void buy() {
        //模拟购买业务
        //先实例化一个用于新增订单的DTO对象
        log.debug("实例化对象：OrderAddDTO");
        OrderAddDTO orderAddDTO = new OrderAddDTO();
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PC100");
        orderAddDTO.setCount(5);
        orderAddDTO.setMoney(100);
        log.debug("新增订单信息为:{}",orderAddDTO);
        //将上面的实例化生成订单
        log.debug("实例化orderAddDTO生成订单");
        dubboOrderService.orderAdd(orderAddDTO);
    }
}
