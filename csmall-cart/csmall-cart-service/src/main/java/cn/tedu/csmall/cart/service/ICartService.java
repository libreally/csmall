package cn.tedu.csmall.cart.service;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;

public interface ICartService {

    void cartAdd(CartAddDTO cartAddDTO);

    void deleteUserCart(String userId,String commodityCode);
}
