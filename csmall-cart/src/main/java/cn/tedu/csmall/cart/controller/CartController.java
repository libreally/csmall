package cn.tedu.csmall.cart.controller;


import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "购物车管理模块")
@RequestMapping("/base/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    public CartController() {
        log.debug("创建控制类：CartController");
    }

    @PostMapping("/add")
    @ApiOperation("新增购物车中的商品")
    public JsonResult cartAdd(CartAddDTO cartAddDTO) {
        cartService.cartAdd(cartAddDTO);
        return JsonResult.ok("新增购物车商品成功");
    }

    @PostMapping("/delete")
    @ApiOperation("删除购物车中的商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户Id", name = "userId", example = "UU100"),
            @ApiImplicitParam(value = "商品编号", name = "commodityCode", example = "PC100")
    })
    public JsonResult deleteUserCart(String userId, String commodityCode) {
        cartService.deleteUserCart(userId, commodityCode);
        return JsonResult.ok("删除购物车商品成功");
    }
}
