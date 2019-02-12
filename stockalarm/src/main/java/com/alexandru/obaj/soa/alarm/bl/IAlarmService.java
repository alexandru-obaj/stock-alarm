package com.alexandru.obaj.soa.alarm.bl;

import com.alexandru.obaj.soa.alarm.dl.model.AlarmEntity;
import com.alexandru.obaj.soa.alarm.sl.model.AlarmDto;

import java.util.List;

/**
 * Interface specifying the contract for the {@link AlarmService} business layer bean.
 */
public interface IAlarmService {

    /**
     * Adds a new alarm to the system.
     *
     * @param storeAlarmRequset the Alarm creation JSON request
     * @return JSON representation of the newly added Alarm
     */
    AlarmDto storeAlarm(AlarmDto storeAlarmRequset);

    /**
     * Updates an already existing alarm in the system.
     *
     * @param userId             the ID of the User making the request
     * @param alarmId            the ID of the alarm to be updated
     * @param updateAlarmRequset the Alarm update JSON request
     */
    void updateAlarm(String userId, int alarmId, AlarmDto updateAlarmRequset);

    /**
     * Retrieves all the alarms defined by the provided User.
     *
     * @param userId the User identifier
     * @return a list of Alarm JSON representations
     */
    List<AlarmDto> retrieveUserAlarms(String userId);

    /**
     * Deletes all the alarms defined by the provided User.
     *
     * @param userId the User identifier
     */
    void deleteUserAlarms(String userId);

    /**
     * Retrieve all defined alarms.
     *
     * @return a list of Alarm JSON representations
     */
    List<AlarmDto> retrieveAllAlarms();

}
