package cn.tedu.csmall.business.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/base/business")
@Api(tags = "业务触发模块")
public class BusinessController {

    @Autowired
    private IBusinessService businessService;

    public BusinessController(){
        log.debug("创建业务控制类：BusinessController");
    }

    @PostMapping("/buy")
    @ApiOperation("执行业务的触发")
    @SentinelResource(value = "业务触发",
                      blockHandler = "blockError",
                      fallback = "fallbackError")
    public JsonResult buy(){
        log.debug("开始执行业务");
        businessService.buy();
        return JsonResult.ok("购买完成");
    }
    //Sentinel自定义限流方法
    public JsonResult blockError(BlockException e){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙，请稍后再试");
    }
    //Sentinel自定义降级方法
    public JsonResult fallbackError(Throwable throwable){
        throwable.printStackTrace();
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务降级");
    }
}
