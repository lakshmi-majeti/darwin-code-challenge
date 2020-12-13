
package com.darwin.response;
import org.springframework.http.HttpStatus;
import java.util.Map;

public class ApiFieldErrorResponse {
    private Map<String, String> errorMessages;
    private HttpStatus status;
    public ApiFieldErrorResponse() {
    }

    public ApiFieldErrorResponse(HttpStatus status, Map<String, String> errorMessages) {
        this.status = status;
        this.errorMessages = errorMessages;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


}
