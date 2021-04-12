package com.example.demo.service;

import com.example.demo.model.StockWrapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    public static final Map<String, StockWrapper> stockesToKeep = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final Duration interval = Duration.ofSeconds(15);

    public CacheService()
    {
        removeOlderStocks();
    }

    private void removeOlderStocks()
    {
        scheduler.scheduleAtFixedRate(()->
                stockesToKeep.forEach((stock, value)->{
                    if(value.getLastAccessedAt().isBefore(LocalDateTime.now().minus(interval)))
                    {
                        stockesToKeep.remove(stock);
                    }
                }),0,15, TimeUnit.MINUTES);
    }

}
