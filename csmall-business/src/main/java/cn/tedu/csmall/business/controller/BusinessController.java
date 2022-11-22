package cn.tedu.csmall.business.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JsonResult buy(){
        log.debug("开始执行业务");
        businessService.buy();
        return JsonResult.ok("购买完成");
    }
}
