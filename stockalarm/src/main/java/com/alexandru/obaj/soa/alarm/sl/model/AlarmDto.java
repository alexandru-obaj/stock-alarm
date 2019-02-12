package com.alexandru.obaj.soa.alarm.sl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class AlarmDto {

    // Property should NOT be serialized
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private int id;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("stockId")
    private String stockId;

    @JsonProperty("initialValue")
    private int initialValue;

    @JsonProperty("increasePercentage")
    private int increasePercentage;

    @JsonProperty("decreasePercentage")
    private int decreasePercentage;

    // Property should NOT be serialized
    @JsonProperty(value = "email", access = JsonProperty.Access.READ_ONLY)
    private String email;

    @JsonCreator
    public AlarmDto(@JsonProperty("userId") String userId, @JsonProperty("stockId") String stockId, @JsonProperty("initialValue") int initialValue,
                    @JsonProperty("increasePercentage") int increasePercentage, @JsonProperty("decreasePercentage") int decreasePercentage) {
        super();
        this.userId = userId;
        this.stockId = stockId;
        this.initialValue = initialValue;
        this.increasePercentage = increasePercentage;
        this.decreasePercentage = decreasePercentage;
    }
}
