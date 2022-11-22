package cn.tedu.csmall.cart.service;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.pojo.cart.model.Cart;

public interface ICartService {

    void cartAdd(CartAddDTO cartAddDTO);

    void deleteUserCart(String userId,String commodityCode);
}
