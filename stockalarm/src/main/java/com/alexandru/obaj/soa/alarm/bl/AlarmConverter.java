package com.alexandru.obaj.soa.alarm.bl;

import com.alexandru.obaj.soa.alarm.dl.model.AlarmEntity;
import com.alexandru.obaj.soa.alarm.sl.model.AlarmDto;

/**
 * Utility class for conversion between REST exchanged POJOs and JPA entities.
 */
public class AlarmConverter {

    public static AlarmEntity toEntity(AlarmDto dto) {
        return new AlarmEntity(0, null, dto.getStockId(), dto.getInitialValue(), dto.getIncreasePercentage(), dto.getDecreasePercentage());
    }

    public static AlarmDto fromEntity(AlarmEntity entity) {
        AlarmDto dto = new AlarmDto(entity.getUser().getUserId(), entity.getStockId(), entity.getIntialValue(),
                entity.getIncreasePercentage(), entity.getDecreasePercentage());
        dto.setEmail(entity.getUser().getEmail());
        return dto;
    }
}
