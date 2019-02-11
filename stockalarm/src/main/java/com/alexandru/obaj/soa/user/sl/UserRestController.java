package com.alexandru.obaj.soa.user.sl;

import com.alexandru.obaj.soa.user.bl.IUserService;
import com.alexandru.obaj.soa.user.sl.model.UserDto;
import com.alexandru.obaj.soa.user.sl.model.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service layer component exposing REST operations for the management of Users in the system.
 */
@RestController
public class UserRestController {

    private IUserService userService;

    @Autowired
    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = UserApiConstants.REQUEST_MAPPING_PATH + "/store")
    public ResponseEntity<UserDto> storeUser(@RequestBody UserDto storeUerRequest) {
        UserDto userResponse = userService.storeUser(storeUerRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            value = UserApiConstants.REQUEST_MAPPING_PATH + "/login")
    public ResponseEntity<Void> loginUser(@RequestBody UserLoginDto userLoginRequest) {
        userService.loginUser(userLoginRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
