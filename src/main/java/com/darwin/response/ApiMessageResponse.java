
package com.darwin.response;


import org.springframework.http.HttpStatus;


public class ApiMessageResponse {
    private String message;
    private HttpStatus status;

    public ApiMessageResponse() {

    }

    public ApiMessageResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


}
