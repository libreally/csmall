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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    // 分页查询所有订单信息的方法
    // page是页码,pageSize是每页条数
    public PageInfo<Order> getAllOrdersByPage(Integer page,Integer pageSize){
        // PageHelper框架实现分页功能的核心代码,是要在执行查询数据库代码运行前
        // 编写PageHelper.startPage(page,pageSize)方法,设置分页的查询条件
        // page是页码从1开始,表示第一页
        PageHelper.startPage(page,pageSize);
        // 上面设置好的分页查询条件,会在下面的查询执行时,sql语句会自动追加limit关键字
        List<Order> list= orderMapper.findAllOrders();

        // 查询结果list只包含查询到的分页数据,并不能包含分页信息(总页数,总条数,有没有上一页下一页等)
        // 所以我们要利用PageHelper提供的PageInfo类型对象来进行返回
        // PageInfo对象既可以包含分页数据,又可以包含分页信息,且是自动计算的
        // 使用方式是在返回时直接实例化即可,构造方法()中直接传入查询到的数据list即可
        return new PageInfo<>(list);

    }
}
