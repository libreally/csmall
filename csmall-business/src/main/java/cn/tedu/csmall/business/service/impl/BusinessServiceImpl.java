package cn.tedu.csmall.business.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusinessServiceImpl implements IBusinessService {


    public BusinessServiceImpl(){
        log.debug("创建业务实现类：BusinessServiceImpl");
    }

    @Override
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
    }
}
