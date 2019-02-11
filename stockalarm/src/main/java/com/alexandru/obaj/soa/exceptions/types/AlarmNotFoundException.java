package com.alexandru.obaj.soa.exceptions.types;

public class AlarmNotFoundException extends RuntimeException {

    private static final String ALARM_NOT_FOUND_MSG = "Alarm with given id was not found";

    public AlarmNotFoundException() {
        super(ALARM_NOT_FOUND_MSG);
    }
}
