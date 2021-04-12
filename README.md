# stock-tickers-microservice

REST application to get stocks from YahooAPI

        <dependency>
            <groupId>com.yahoofinance-api</groupId>
            <artifactId>YahooFinanceAPI</artifactId>
            <version>3.12.3</version>
        </dependency>

Stockes received from remote API are saved in cache and after every 15 minutes cache is getting refreshed.
If stockes are older than 15 minutes then its removed.

Run the application:

Install the application: mvn clean install
1- run the application using jar file (java -jar jarname)
or
2-run the DemoApplication class in editor

API
1- http://localhost:8080/stocks/tickerName
e,g http:localhost:8080/stocks/GOOG  (to get stocks from google)

2- http://localhost:8080/stocks/findAll
e,g method POST
request body is ["GOOG","MOS"]

3 @GetMapping("stocks/price/{ticker}") 
http://localhost:8080/stocks/price/GOOG

