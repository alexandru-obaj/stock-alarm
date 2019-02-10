package com.alexandru.obaj.soa.user.bl;

import com.alexandru.obaj.soa.user.sl.model.UserDto;
import com.alexandru.obaj.soa.user.sl.model.UserLoginDto;

/**
 * Interface specifying the contract for the {@link UserService} business layer bean.
 */
public interface IUserService {

    /**
     * Adds a new User to the system.
     *
     * @param userRequest the User creation JSON request
     * @return JSON representation of the newly added User
     */
    UserDto storeUser(UserDto userRequest);

    /**
     * Logs in a User already stored in the system based on his credentials.
     *
     * @param loginRequest the User login JSON request
     */
    void loginUser(UserLoginDto loginRequest);

}
