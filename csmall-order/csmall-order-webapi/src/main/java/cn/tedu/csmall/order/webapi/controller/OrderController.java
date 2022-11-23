package cn.tedu.csmall.order.webapi.controller;


import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "订单管理模块")
@RequestMapping("/base/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    public OrderController() {
        log.debug("创建控制类：CartController");
    }

    @PostMapping("/add")
    @ApiOperation("新增订单模块")
    public JsonResult cartAdd(OrderAddDTO orderAddDTO) {
        orderService.orderAdd(orderAddDTO);
        return JsonResult.ok("新增订单成功");
    }
}
