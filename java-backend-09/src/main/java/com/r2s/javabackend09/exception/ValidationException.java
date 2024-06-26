package com.r2s.javabackend09.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public ValidationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
