package com.github.juliherms.vehiclesapi.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Class responsible to customize errors from API
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private final String message;
    private final List<String> errors;

    ErrorMessage(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
