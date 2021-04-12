package com.example.demo.service;

import com.example.demo.model.StockWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@AllArgsConstructor
@Slf4j
@Service
public class StockService {

    private Map<String, StockWrapper> cachedStocks = CacheService.stockesToKeep;

    public StockWrapper findStock(final String ticker) {
        log.info("get stock quote from for ticker "+ticker);
        StockWrapper stockWrapper = null;

                try{
                    stockWrapper = cachedStocks.get(ticker);
                    if(stockWrapper == null) {
                        stockWrapper = new StockWrapper(YahooFinance.get(ticker));
                        cachedStocks.put(ticker, stockWrapper);
                    }
                }
                catch(Exception e)
                {
                  log.error(e.getLocalizedMessage());
                }

                return stockWrapper;
    }

    public List<StockWrapper> findStocks(final List<String> tickers) {


        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public BigDecimal findStockPrice(final StockWrapper stockWrapper) throws IOException
    {
        return stockWrapper.getStock().getQuote(true).getPrice();
    }
}
