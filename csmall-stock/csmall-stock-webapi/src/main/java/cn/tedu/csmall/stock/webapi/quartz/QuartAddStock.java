package cn.tedu.csmall.stock.webapi.quartz;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class QuartAddStock implements Job {

    @Autowired
    private IStockService stockService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        StockReduceCountDTO countDTO=new StockReduceCountDTO();
        countDTO.setCommodityCode("PC100");
        countDTO.setReduceCount(-10);
        stockService.reduceCommodityCount(countDTO);
        log.info("Quartz定时任务增加库存完成!");
    }
}
