package cn.tedu.csmall.order.service;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.commons.restful.JsonPage;

public interface IOrderService {

    void orderAdd(OrderAddDTO orderAddDTO);

    //返回jsonPage类型的分页查询订单方法
    JsonPage<Order> getAllOrdersByPage(Integer page,Integer pageSize);
}
