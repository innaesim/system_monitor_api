package com.monitor.utility.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int status;
    private String exception;
    private String message;
    private List<FieldError> errors;

    @Getter
    @Setter
    public static class FieldError {
        private String field;
        private String error;
    }
}
