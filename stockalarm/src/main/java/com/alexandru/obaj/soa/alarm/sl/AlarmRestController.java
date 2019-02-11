package com.alexandru.obaj.soa.alarm.sl;

import com.alexandru.obaj.soa.alarm.bl.IAlarmService;
import com.alexandru.obaj.soa.alarm.sl.model.AlarmDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Service layer component exposing REST operations for the management of User defined Stock Alarms
 * in the system.
 */
@RestController
public class AlarmRestController {

    private IAlarmService alarmService;

    public AlarmRestController(IAlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            value = AlarmApiConstants.REQUEST_MAPPING_PATH + "/all")
    public ResponseEntity<List<AlarmDto>> retrieveAlarms(@RequestParam("userId") String userId) {
        List<AlarmDto> userAlarms = alarmService.retrieveUserAlarms(userId);
        return new ResponseEntity<>(userAlarms, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = AlarmApiConstants.REQUEST_MAPPING_PATH + "/store")
    public ResponseEntity<AlarmDto> createAlarm(@RequestBody AlarmDto storeAlarmRequest) {
        AlarmDto storeAlarmResponse = alarmService.storeAlarm(storeAlarmRequest);
        return new ResponseEntity<>(storeAlarmResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            value = AlarmApiConstants.REQUEST_MAPPING_PATH + "/{id}/update")
    public ResponseEntity<Void> updateAlarm(@RequestBody AlarmDto updateAlarmRequest, @PathVariable("id") int alarmId, @RequestParam("userId") String userId) {
        alarmService.updateAlarm(userId, alarmId, updateAlarmRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,
            value = AlarmApiConstants.REQUEST_MAPPING_PATH + "/delete")
    public ResponseEntity<List<AlarmDto>> deleteAlarms(@RequestParam("userId") String userId) {
        alarmService.deleteUserAlarms(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
