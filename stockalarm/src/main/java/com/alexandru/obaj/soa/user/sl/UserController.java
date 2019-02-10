package com.alexandru.obaj.soa.user.sl;

import com.alexandru.obaj.soa.user.bl.IUserService;
import com.alexandru.obaj.soa.user.sl.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service layer component exposing REST operations for the management of Users in the system.
 */
@RestController
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = UserApiConstants.REQUEST_MAPPING_PATH + "/store")
    public UserDto storeUser(@RequestBody UserDto userRequest) {
        return userService.storeUser(userRequest);
    }
}
