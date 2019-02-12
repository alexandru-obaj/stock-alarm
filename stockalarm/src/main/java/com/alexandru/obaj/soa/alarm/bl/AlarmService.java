package com.alexandru.obaj.soa.alarm.bl;

import com.alexandru.obaj.soa.alarm.dl.IAlarmDao;
import com.alexandru.obaj.soa.alarm.dl.model.AlarmEntity;
import com.alexandru.obaj.soa.alarm.sl.model.AlarmDto;
import com.alexandru.obaj.soa.exceptions.types.AccessDeniedException;
import com.alexandru.obaj.soa.exceptions.types.AlarmNotFoundException;
import com.alexandru.obaj.soa.user.bl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Business layer component managing Stock Alarm related operations.
 */
@Service
@Transactional
public class AlarmService implements IAlarmService {

    private IAlarmDao alarmDao;

    private IUserService userService;

    @Autowired
    public AlarmService(IAlarmDao alarmDao, IUserService userService) {
        this.alarmDao = alarmDao;
        this.userService = userService;
    }

    @Override
    public AlarmDto storeAlarm(AlarmDto storeAlarmRequset) {
        validateCreateRequest(storeAlarmRequset);
        return AlarmConverter.fromEntity(alarmDao.saveAndFlush(AlarmConverter.toEntity(storeAlarmRequset)));
    }

    @Override
    public void updateAlarm(String userId, int alarmId, AlarmDto updateAlarmRequset) {
        validateUpdateRequest(userId, alarmId, updateAlarmRequset);
        alarmDao.saveAndFlush(AlarmConverter.toEntity(updateAlarmRequset));
    }

    @Override
    public List<AlarmDto> retrieveUserAlarms(String userId) {
        return alarmDao.findByUserId(userId).stream().map(alarmEntity
                -> AlarmConverter.fromEntity(alarmEntity)).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteUserAlarms(String userId) {
        alarmDao.deleteByUserId(userId);
    }

    @Override
    public List<AlarmDto> retrieveAllAlarms() {
        return alarmDao.findAll().stream().map(alarmEntity
                -> AlarmConverter.fromEntity(alarmEntity)).collect(Collectors.toList());
    }

    private void validateCreateRequest(AlarmDto storeAlarmRequset) {
        Assert.notNull(storeAlarmRequset, "Alarm description is mandatory");
        Assert.notNull(userService.retrieveUser(storeAlarmRequset.getUserId()), "The provided USer does not exist");
        Assert.hasText(storeAlarmRequset.getStockId(), "Stock ID is mandatory");
    }

    private void validateUpdateRequest(String userId, int alarmId, AlarmDto updateAlarmRequset) {
        validateCreateRequest(updateAlarmRequset);
        Assert.isTrue(updateAlarmRequset.getUserId().equals(userId), "You can not assign the alarm to another User through update");

        if (alarmDao.findById(alarmId) == null) {
            throw new AlarmNotFoundException();
        }
        if (!alarmDao.findById(alarmId).get().getUser().getUserId().equals(userId)) {
            throw new AccessDeniedException();
        }
    }
}
