package com.api.readdatabase.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ValidationException extends RuntimeException {

	private String code;
	private String description;

	public ValidationException(String code, String description) {
		this.code = code;
		this.description = description;
	}

}
