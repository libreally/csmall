package cn.tedu.csmall.cart.service.impl;

import cn.tedu.csmall.cart.mapper.CartMapper;
import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import cn.tedu.csmall.commons.restful.ResponseCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    public CartServiceImpl(){
        log.debug("实现业务：CartServiceImpl");
    }

    @Override
    public void cartAdd(CartAddDTO cartAddDTO) {
        log.debug("新增购物车商品");
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartAddDTO,cart);
        int rows = cartMapper.insert(cart);
        if (rows != 1) {
            String message = "添加商品失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, message);
        }
        log.debug("新增呢购物车商品完成：{}",cart);
    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        log.debug("开始删除购物车商品");
        cartMapper.deleteCartByUserIdAndCommodityCode(userId,commodityCode);
        log.debug("删除购物车商品成功");

    }
}
