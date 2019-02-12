package com.alexandru.obaj.soa.notification;

import com.alexandru.obaj.soa.alarm.sl.model.AlarmDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AlarmProcessor implements IAlarmProcessor {

    private INotificationService notificationService;

    @Autowired
    public AlarmProcessor(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @Async("asyncAlarmProcessor")
    public void processAlarm(AlarmDto alarmDto, int stockPrice) {
        if (isStockTriggerMet(alarmDto, stockPrice)) {
            log.info("Notifying User {}", alarmDto.getEmail());
            notificationService.sendMail(alarmDto.getEmail(), alarmDto.getStockId(), stockPrice);
        }
    }


    /**
     * Determines whether or not notification conditions are met for the given stock.
     * @param alarmDto
     * @param stockPrice
     * @return
     */
    private boolean isStockTriggerMet(AlarmDto alarmDto, int stockPrice) {
        boolean result = false;
        if (alarmDto.getInitialValue() == 0) {
            result = (stockPrice >= Math.abs(alarmDto.getIncreasePercentage()))
                    || (stockPrice >= Math.abs(alarmDto.getDecreasePercentage()));
        } else {
            double pricePercentageChange = Math.abs(100 - (stockPrice * 100) / alarmDto.getInitialValue());
            result = (pricePercentageChange >= Math.abs(alarmDto.getIncreasePercentage()))
                    || (pricePercentageChange >= Math.abs(alarmDto.getDecreasePercentage()));
        }
        return result;
    }
}
