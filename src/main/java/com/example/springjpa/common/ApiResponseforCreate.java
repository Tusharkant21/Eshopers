package com.example.springjpa.common;

import java.time.LocalDateTime;

public class ApiResponseforCreate {
    private final boolean success;
    private final String message;

    public ApiResponseforCreate(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp(){
        return LocalDateTime.now().toString();
    }
}
