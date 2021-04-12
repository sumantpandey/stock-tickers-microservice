package com.example.demo.service;

import com.example.demo.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockservice;

    @Test
    void findStock(){
         StockWrapper sw = stockservice.findStock("RELL");
         assertNotNull(sw);
    }

    @Test
    void findStocks(){
        List<StockWrapper> stockList = stockservice.findStocks(Arrays.asList("RELL","GOOG","MOS"));
        assertNotNull(stockList);
        assertEquals(3, stockList.size());
    }

    @Test
    void findStockPrice() throws IOException {
        StockWrapper sw = stockservice.findStock("GOOG");
        BigDecimal price = stockservice.findStockPrice(sw);
        assertNotNull(price);

    }
}