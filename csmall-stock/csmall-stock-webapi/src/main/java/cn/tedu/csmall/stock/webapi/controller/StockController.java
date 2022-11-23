package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.stock.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/base/stock")
@Api(tags = "库存管理模块")
public class StockController {

    @Autowired
    private IStockService stockService;

    public StockController(){
        log.debug("创建控制类：StockController");
    }

    @PostMapping("/reduce/count")
    @ApiOperation("减少库存数")
    public JsonResult reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO){
        stockService.reduceCommodityCount(stockReduceCountDTO);
        return JsonResult.ok("库存减少完成");
    }
}
