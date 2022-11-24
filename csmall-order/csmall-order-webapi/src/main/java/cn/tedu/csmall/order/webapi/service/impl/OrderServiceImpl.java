package cn.tedu.csmall.order.webapi.service.impl;

import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.order.webapi.mapper.OrderMapper;
import cn.tedu.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@DubboService
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @DubboReference
    private ICartService cartService;

    @DubboReference
    private IStockService stockService;


    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        log.debug("先减少订单中商品库存数量stock模块");
        //先减少订单中商品库存数量stock模块
        StockReduceCountDTO stockReduceCountDTO = new StockReduceCountDTO();
        stockReduceCountDTO.setReduceCount(orderAddDTO.getCount());
        stockReduceCountDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        stockService.reduceCommodityCount(stockReduceCountDTO);
        log.debug("从购物车中删除用户勾选的模块cart模块");
        //从购物车中删除用户勾选的模块cart模块
        cartService.deleteUserCart(orderAddDTO.getUserId(),orderAddDTO.getCommodityCode());

        if (Math.random()<0.5){
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR,"发生随机异常");
        }
        log.debug("新增订单");
        //新增订单
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        orderMapper.insertOrder(order);
        log.debug("新增订单信息成功");
    }
}
