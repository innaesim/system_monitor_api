package com.monitor.utility.commons;

import lombok.Data;

@Data
public class ApiResponse <T> {
    private int status;
    private String responseCode;
    private String message;
    private T body;

    public ApiResponse(int status, String responseCode, String message, T body) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
        this.body = body;
    }

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(int status, String responseCode, String message) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
    }

    public ApiResponse(int status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

}
