package com.alexandru.obaj.soa.exceptions.types;

public class AccessDeniedException extends RuntimeException {

    private static final String ACCESS_DENIED_MSG = "User did not define given alarm";

    public AccessDeniedException() {
        super(ACCESS_DENIED_MSG);
    }
}
