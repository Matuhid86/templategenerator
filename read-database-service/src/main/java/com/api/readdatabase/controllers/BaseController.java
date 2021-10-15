package com.api.readdatabase.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.readdatabase.dtos.ErrorDto;
import com.api.readdatabase.exceptions.ValidationException;

public interface BaseController {

	@ExceptionHandler({ Exception.class })
	default public ResponseEntity<ErrorDto> handleException(Exception exception) {
		ErrorDto response = new ErrorDto();
		response.setCode("999");
		response.setMessage(exception.getMessage());

		if (exception instanceof ValidationException) {
			ValidationException validationException = (ValidationException) exception;
			response.setCode(validationException.getCode());
			response.setMessage(validationException.getDescription());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
