package com.fastx.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest w) {
        ErrorDetails er = new ErrorDetails(LocalDateTime.now(), e.getMessage(), w.getDescription(false), "Not_found");
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Error> userNotFoundException(UserNotFoundException userEx,WebRequest web){
		
		Error error = new Error(LocalDateTime.now(),userEx.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<Error> routeException(RouteNotFoundException routeEx,WebRequest web){
		
		Error error = new Error(LocalDateTime.now(),routeEx.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Error> bookingException(BookingNotFoundException reservationEx,WebRequest web){
		
		Error error = new Error(LocalDateTime.now(),reservationEx.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BusOperatorNotFoundException.class)
	public ResponseEntity<Error> busOperatorNotFoundException(BusOperatorNotFoundException reservationEx,WebRequest web){
		
		Error error = new Error(LocalDateTime.now(),reservationEx.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<Error> busException(BusNotFoundException busEx,WebRequest web){
		
		Error error = new Error(LocalDateTime.now(),busEx.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Error> badRequestException(BadRequestException badEx,WebRequest web){
		
		Error error = new Error(LocalDateTime.now(),badEx.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        
        List<ObjectError> errList = ex.getBindingResult().getAllErrors();
        errList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}