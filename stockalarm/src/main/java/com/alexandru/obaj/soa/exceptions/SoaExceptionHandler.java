package com.alexandru.obaj.soa.exceptions;

import com.alexandru.obaj.soa.exceptions.model.ErrorResponse;
import com.alexandru.obaj.soa.exceptions.types.AccessDeniedException;
import com.alexandru.obaj.soa.exceptions.types.AlarmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class SoaExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> processBadRequests(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> processForbiddenRequests(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(value = {AlarmNotFoundException.class})
    public ResponseEntity<Object> processNotFoundRequests(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
