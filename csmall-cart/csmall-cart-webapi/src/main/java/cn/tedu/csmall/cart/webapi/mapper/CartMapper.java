package cn.tedu.csmall.cart.webapi.mapper;

import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper {
    //新增购物车商品的方法
    @Insert("insert into csmall_db.cart_tbl(commodity_code, price, count, user_id) " +
            "values(#{commodityCode},#{price},#{count},#{userId})")
    int insert(Cart cart);

    //删除购物车商品的方法
    @Delete("delete from csmall_db.cart_tbl where commodity_code=#{commodityCode} and user_id=#{userId}")
    int deleteCartByUserIdAndCommodityCode(@Param("userId") String userId,@Param("commodityCode") String commodityCode);
}
