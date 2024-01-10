package com.project.ticketmntsys.exceptonalhandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> validation(MethodArgumentNotValidException ma)
	{
		Map<String,String> errorMap = new HashMap<>();
		ma.getBindingResult().getFieldErrors().forEach(error -> {
		errorMap.put(error.getField(),error.getDefaultMessage());
	});
		return errorMap;
		
	}
}
