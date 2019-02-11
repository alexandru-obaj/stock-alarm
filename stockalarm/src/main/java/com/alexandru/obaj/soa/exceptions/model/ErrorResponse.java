package com.alexandru.obaj.soa.exceptions.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse {

    @JsonProperty("errorMessage")
    private String errorMsg;

    @JsonCreator
    public ErrorResponse(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }
}
