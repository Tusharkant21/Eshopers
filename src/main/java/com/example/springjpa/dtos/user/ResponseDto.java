package com.example.springjpa.dtos.user;

public class ResponseDto {

    private String Staus;
    private String message;

    public ResponseDto(String staus, String message) {
        Staus = staus;
        this.message = message;
    }

    public ResponseDto() {
    }

    public String getStaus() {
        return Staus;
    }

    public void setStaus(String staus) {
        Staus = staus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
