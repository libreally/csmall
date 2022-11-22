package cn.tedu.csmall.order.mapper;

import cn.tedu.csmall.commons.pojo.order.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    @Insert("insert into csmall_db.order_tbl(user_id, commodity_code, count, money)" +
            "values(#{userId},#{commodityCode},#{count},#{money}) ")
    int insertOrder(Order order);
}
