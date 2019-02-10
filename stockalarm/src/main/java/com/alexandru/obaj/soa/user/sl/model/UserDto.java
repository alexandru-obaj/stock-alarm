package com.alexandru.obaj.soa.user.sl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO describing the JSON representation of a User REST resource.
 */
@Data
public class UserDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("emailAddress")
    private String email;

    /**
     * Explicit all-args constructor used as default by JACKSON.
     *
     * @param userId    the system username of the end user
     * @param firstName the first name of the end user
     * @param lastName  the last name of the end user
     * @param password  the password of the end user
     * @param email     the email of the end user
     */
    @JsonCreator
    public UserDto(@JsonProperty("userId") String userId, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
                   @JsonProperty("password") String password, @JsonProperty("emailAddress") String email) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
