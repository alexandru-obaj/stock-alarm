package com.alexandru.obaj.soa.client;

import com.alexandru.obaj.soa.client.model.StockDescription;
import com.alexandru.obaj.soa.config.SoaHttpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class RemoteStockClient implements IStockClient {

    private static final String STOCK_SYMBOL_KEY = "{symbol}";

    private RestTemplate restTemplate;

    private SoaHttpConfig soaHttpConfig;

    @Autowired
    public RemoteStockClient(RestTemplate restTemplate, SoaHttpConfig soaHttpConfig) {
        this.restTemplate = restTemplate;
        this.soaHttpConfig = soaHttpConfig;
    }

    @Override
    public List<StockDescription> getRefferenceStocks() {
        ResponseEntity<List<StockDescription>> response = restTemplate.exchange(resolveUrl(null), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StockDescription>>() {
                });
        return response.getBody();
    }

    @Override
    public Integer getStockPrice(String symbol) {
        Integer result = null;
        try {
            ResponseEntity<Integer> response = restTemplate.exchange(resolveUrl(symbol), HttpMethod.GET, null,
                    new ParameterizedTypeReference<Integer>() {
                    });
            result = new Integer(response.getBody());
        } catch (RestClientException re) {
            log.warn("Retrieving stock price for symbol {} failed and can not be counted upon for this scheduled check", symbol);
        }
        return result;
    }

    private String resolveUrl(String stockSymbol) {
        String requestUrl = soaHttpConfig.getBaseUrl();
        if (stockSymbol == null) {
            requestUrl = new StringBuffer(requestUrl).append("/").
                    append(soaHttpConfig.getRefDataSymbolsSufix()).toString();
        } else {
            requestUrl = new StringBuffer(requestUrl).append("/")
                    .append(soaHttpConfig.getSymbolPriceSufix()
                            .replace(STOCK_SYMBOL_KEY, stockSymbol)).toString();
        }

        return requestUrl;
    }


}
