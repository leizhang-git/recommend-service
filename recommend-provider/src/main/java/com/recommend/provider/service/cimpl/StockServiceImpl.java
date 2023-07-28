package com.recommend.provider.service.cimpl;

import com.recommend.consumer.dao.StockMapper;
import com.recommend.consumer.domain.Stock;
import com.recommend.consumer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe 用于测试分布式锁
 * @Author zhanglei
 * @Date 2023/7/17 9:43
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;
    ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public void checkAndLock() {
        reentrantLock.lock();
        Stock stock = stockMapper.selectById(1L);

        if(stock != null && stock.getCount() > 0) {
            stock.setCount(stock.getCount() - 1);
            stockMapper.updateById(stock);
        }
        reentrantLock.unlock();
    }
}
