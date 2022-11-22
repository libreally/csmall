package cn.tedu.csmall.order.service.impl;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.order.mapper.OrderMapper;
import cn.tedu.csmall.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        //先减少订单中商品库存数量stock模块

        //从购物车中删除用户勾选的模块cart模块

        //新增订单
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        orderMapper.insertOrder(order);
        log.debug("新增订单信息");
    }
}
