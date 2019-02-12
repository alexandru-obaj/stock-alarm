package com.alexandru.obaj.soa.notification;

import com.alexandru.obaj.soa.alarm.bl.IAlarmService;
import com.alexandru.obaj.soa.alarm.sl.model.AlarmDto;
import com.alexandru.obaj.soa.client.IStockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@EnableAsync
@EnableScheduling
public class StockAlarmScheduler {

    private static final String DELAY = "${soa.scheduler.reload-interval}";

    private IAlarmService alarmService;

    private IStockClient stockClient;

    private IAlarmProcessor alarmProcessorHandler;

    private Map<String, Integer> stockPrices;

    @Autowired
    public StockAlarmScheduler(IAlarmService alarmService, IAlarmProcessor alarmProcessorHandler,
                               IStockClient stockClient) {
        this.alarmService = alarmService;
        this.alarmProcessorHandler = alarmProcessorHandler;
        this.stockClient = stockClient;
        this.stockPrices = new HashMap<>();
    }

    @Scheduled(initialDelayString = DELAY, fixedDelayString = DELAY)
    public void processAlarms() {
        List<AlarmDto> configuredAlarms = alarmService.retrieveAllAlarms();
        Set<String> distinctStocks = configuredAlarms.stream().map(alarmEntity -> alarmEntity.getStockId()).
                collect(Collectors.toList()).stream().collect(Collectors.toSet());
        distinctStocks.stream().forEach(stockSymbol -> registerStockPrices(stockSymbol));
        configuredAlarms.forEach(alarmDto ->
                alarmProcessorHandler.processAlarm(alarmDto, stockPrices.get(alarmDto.getStockId())));
    }

    private void registerStockPrices(String stockSymbol) {
        Integer stockPrice = stockClient.getStockPrice(stockSymbol);
        if (stockPrice != null) {
            stockPrices.put(stockSymbol, stockPrice);
        }
    }
}
