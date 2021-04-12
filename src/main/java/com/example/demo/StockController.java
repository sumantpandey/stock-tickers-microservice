package com.example.demo;

import com.example.demo.exception.StockNotFoundException;
import com.example.demo.model.StockWrapper;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockservice;

    @GetMapping("/{ticker}")
    public ResponseEntity<StockWrapper> getStock(@PathVariable("ticker") String ticker) {

        StockWrapper stock = stockservice.findStock(ticker);

        if(stock.getStock() == null) {
            throw new StockNotFoundException("Invalid ticker symbol : " + ticker);
        }
        return new ResponseEntity<StockWrapper>(stock, HttpStatus.OK);

    }

    @GetMapping("/{ticker}")
    public ResponseEntity<List<StockWrapper>> getStock(@RequestParam(value="tickers") List<String> tickers) {

        List<StockWrapper> stock = stockservice.findStocks(tickers);

        return new ResponseEntity<List<StockWrapper>>(stock, HttpStatus.OK);

    }

    @GetMapping("/{ticker}")
    public ResponseEntity<BigDecimal> getStockPrice(@PathVariable("ticker") String ticker) throws IOException {

        StockWrapper sw = stockservice.findStock(ticker);

        return new ResponseEntity<BigDecimal>(stockservice.findStockPrice(sw), HttpStatus.OK);

    }
}
