package com.alexandru.obaj.soa.notification;

import com.alexandru.obaj.soa.alarm.sl.model.AlarmDto;

/**
 * Interface specifying the contract for the alarm processor bean.
 */
public interface IAlarmProcessor {

    /**
     * Process the given alarm and decide whether a User notification is in order.
     *
     * @param alarmDto   POJO describing the User configured stock alarm
     * @param stockPrice the current price of the stock
     */
    void processAlarm(AlarmDto alarmDto, int stockPrice);
}
