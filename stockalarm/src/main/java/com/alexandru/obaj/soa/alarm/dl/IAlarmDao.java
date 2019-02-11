package com.alexandru.obaj.soa.alarm.dl;

import com.alexandru.obaj.soa.alarm.dl.model.AlarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Alarm entity Database Access Object.
 */
@Repository
public interface IAlarmDao extends JpaRepository<AlarmEntity, Integer> {

    List<AlarmEntity> findByUserId(@Param("userId") String userId);

    @Modifying
    void deleteByUserId(@Param("userId") String userId);
}
