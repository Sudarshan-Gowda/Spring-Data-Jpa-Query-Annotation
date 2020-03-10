/**
 * 
 */
package com.star.sud.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sudarshan
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061069618007219168L;

	public EmployeeCreationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
