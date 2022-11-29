package cn.tedu.csmall.order.webapi.mapper;

import cn.tedu.csmall.commons.pojo.order.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    @Insert("insert into csmall_db.order_tbl(user_id, commodity_code, count, money)" +
            "values(#{userId},#{commodityCode},#{count},#{money}) ")
    int insertOrder(Order order);
    // 分页查询所有订单的方法
    // PageHelper框架完成分页的原理是运行的sql语句后自动添加limit关键字
    // 所以我们在编写查询方法时,无需关注分页操作,和普通查询没有区别(注解和xml文件都是)
    @Select("select id,user_id,commodity_code,count,money from csmall_db.order_tbl")
    List<Order> findAllOrders();
}
