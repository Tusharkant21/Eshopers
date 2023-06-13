package com.example.springjpa.common;

import java.time.LocalDateTime;

public class ApiResponse {

    private final boolean success;
    private final String message;
    private final  int idnumber;

    public ApiResponse(boolean success, String message, int idnumber) {
        this.success = success;
        this.message = message;
        this.idnumber=idnumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getIdnumber() {
        return idnumber;
    }

    public String getTimestamp(){
        return LocalDateTime.now().toString();
    }
}
