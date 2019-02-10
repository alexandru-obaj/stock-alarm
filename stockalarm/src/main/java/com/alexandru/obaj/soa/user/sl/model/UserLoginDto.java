package com.alexandru.obaj.soa.user.sl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLoginDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("password")
    private String password;

    public UserLoginDto(@JsonProperty("userId") String userId, @JsonProperty("password") String password) {
        super();
        this.userId = userId;
        this.password = password;
    }
}
