package com.fmss.automotiveecombackend.exception.handler;

import com.fmss.automotiveecombackend.data.dto.response.NotFoundResponsePayload;
import com.fmss.automotiveecombackend.exception.exception_classes.ClientBadRequestException;
import com.fmss.automotiveecombackend.exception.exception_classes.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundResponsePayload handleNotFound(NotFoundException notFoundException) {
        return NotFoundResponsePayload.builder()
                .message(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(ClientBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public NotFoundResponsePayload handleBadRequest(ClientBadRequestException exception) {
        return NotFoundResponsePayload.builder()
                .message(exception.getMessage())
                .build();
    }
}
