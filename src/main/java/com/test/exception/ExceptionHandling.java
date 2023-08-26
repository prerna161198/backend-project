package com.test.exception;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandling{
	
	Map<String , String> response=new HashMap<>();
	
	  @ExceptionHandler(HttpMessageNotReadableException.class) public
	  ResponseEntity<Map>
	  HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e,
	  WebRequest request) { response.put("date", new Date().toLocaleString());
	  response.put("error", e.getMessage());
	  response.put("description","problem with data format");
	  
	  return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST); }
	 
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map> ConstraintViolationExceptionHandler(ConstraintViolationException e, WebRequest request)
	{
		response.put("date", new Date().toLocaleString());
		response.put("error", e.getMessage());
		response.put("description","problem with data format");
		
		return new ResponseEntity<Map>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e, WebRequest request)
	{
		response.put("date", new Date().toLocaleString());
		response.put("error", e.getMessage());
		response.put("description",e.getCause().getLocalizedMessage());
		
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e)
	{
		e.getBindingResult().getAllErrors().forEach((error)->{
				
				String fieldName=((FieldError)error).getField();
				String message=error.getDefaultMessage();
				response.put(fieldName, message);
		});
		

		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map> genericExceptionHandler(Exception e)
	{
		response.put("date", new Date().toLocaleString());
		response.put("error", e.getMessage());
		response.put("description","problem with data format");
		
		return new ResponseEntity<Map>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

