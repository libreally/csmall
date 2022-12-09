package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.stock.service.IStockService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/stock")
@Api(tags = "库存管理模块")
public class StockController {

    @Autowired
    private IStockService stockService;

    @PostMapping("/reduce/count")
    @ApiOperation("减少库存数")
    // @SentinelResource注解需要标记在控制层方法上,在该方法第一次运行后,会被Sentinel仪表台检测
    // 该方法在运行前,不会出现在仪表台中
    // 括号中"减少库存数"这个描述会出现在仪表台上,代表这个方法
    // blockHandler可以设置当前控制器方法被限流时,要运行的自定义限流方法,blockError就是方法名称
    @SentinelResource(  value = "减少库存数",
            blockHandler = "blockError",
            fallback = "fallbackError")
    public JsonResult reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO){
        // 调用业务逻辑层
        stockService.reduceCommodityCount(stockReduceCountDTO);
        // 随机发生异常,测试自定义降级方法效果
        if(Math.random()<0.5){
            throw new CoolSharkServiceException(
                    ResponseCode.INTERNAL_SERVER_ERROR,"发生随机异常");
        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return JsonResult.ok("库存减少完成!");
    }

    // Sentinel自定义限流方法定义规则
    // 1.访问修饰符必须是public
    // 2.返回值类型必须和控制器方法一致
    // 3.方法名必须是控制器方法注解中由blockHandler标记的方法名称
    // 4.方法的参数必须包含控制器方法的所有参数,再额外添加一个BlockException的异常参数类型
    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO,
                                 BlockException e){
        // 这个方法运行表示当前请求被限流了,我们给与返回,提示它被限流即可
        return JsonResult.failed(
                ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙,请稍后再试");
    }

    // 自定义降级方法: 由@SentinelResource的fallback属性指定
    // 自定义降级方法和自定义限流方法的格式规则基本一致
    // 只是额外返回的异常类型使用Throwable
    // 当上面的控制层方法发生异常时,会调用自定义降级方法
    // 实际开发中,可能调用运行一些老版本的代码,所以称之为"降级"
    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO,
                                    Throwable throwable){
        // 输入异常信息
        throwable.printStackTrace();
        // 返回降级方法运行的信息
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务降级");
    }
}






