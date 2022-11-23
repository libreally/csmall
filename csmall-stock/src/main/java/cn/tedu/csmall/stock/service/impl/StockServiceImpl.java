package cn.tedu.csmall.stock.service.impl;


import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.stock.mapper.StockMapper;
import cn.tedu.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    private StockMapper stockMapper;

    public StockServiceImpl(){
        log.debug("创建业务实现类：StockServiceImpl");
    }

    @Override
    public void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        log.debug("开始执行减少库存业务");
        Integer reduceCount = stockReduceCountDTO.getReduceCount();
        String commodityCode = stockReduceCountDTO.getCommodityCode();
        int rows = stockMapper.updateStockCount(commodityCode, reduceCount);
        if (rows==0){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"库存不足");
        }
        log.debug("减少库存完成");
    }
}
