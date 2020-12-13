package com.darwin.exception;


import com.darwin.response.ApiFieldErrorResponse;
import com.darwin.response.ApiMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiMessageResponse> handle(InvalidDataException e)
    {
        return new ResponseEntity<>(new ApiMessageResponse(HttpStatus.BAD_REQUEST, e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> handle(ResourceNotFoundException e)
    {
        return new ResponseEntity<>(new ApiMessageResponse(HttpStatus.NOT_FOUND, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiFieldErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ApiFieldErrorResponse(HttpStatus.BAD_REQUEST, errors),
                HttpStatus.BAD_REQUEST);
    }
}
