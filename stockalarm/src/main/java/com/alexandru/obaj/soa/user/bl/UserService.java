package com.alexandru.obaj.soa.user.bl;

import com.alexandru.obaj.soa.user.dl.IUserDao;
import com.alexandru.obaj.soa.user.sl.model.UserDto;
import com.alexandru.obaj.soa.user.sl.model.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



/**
 * Business layer component managing User related operations.
 */
@Service
public class UserService implements IUserService {

    private IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public UserDto storeUser(UserDto userRequest) {
        validateUserCreationRequest(userRequest);
        Assert.isNull(userDao.findByUserId(userRequest.getUserId()), "There is already a User with that userId in the system");
        return UserConverter.fromEntity(userDao.saveAndFlush(UserConverter.toEntity(userRequest)));
    }

    @Override
    @Transactional
    public void loginUser(UserLoginDto loginRequest) {
        validateUserLoginRequest(loginRequest);
        Assert.notNull(userDao.findByUserIdAndPassword(loginRequest.getUserId(), loginRequest.getPassword()), "Username or password was incorrect");
    }

    private void validateUserCreationRequest(UserDto creationRequest) {
        Assert.notNull(creationRequest, "Request can not be null");
        Assert.hasText("UserId is mandatory", creationRequest.getUserId());
        Assert.hasText("User password is mandatory", creationRequest.getPassword());
    }

    private void validateUserLoginRequest(UserLoginDto loginRequest) {
        Assert.notNull(loginRequest, "Request can not be null");
        Assert.hasText("UserId is mandatory", loginRequest.getUserId());
        Assert.hasText("User password is mandatory", loginRequest.getPassword());
    }
}
