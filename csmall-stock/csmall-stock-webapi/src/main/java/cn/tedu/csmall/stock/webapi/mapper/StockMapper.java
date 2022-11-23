package cn.tedu.csmall.stock.webapi.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {
    //修改指定商品的库存数
    @Update("update csmall_db.stock_tbl set count=count-#{reduceCount} " +
            "where commodity_code=#{commodityCode} and count>=#{reduceCount}")
    int updateStockCount(@Param("commodityCode") String commodityCode,
                         @Param("reduceCount") Integer reduceCount);
}
