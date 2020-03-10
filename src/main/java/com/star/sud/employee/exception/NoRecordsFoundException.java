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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRecordsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6149692937325296594L;

	public NoRecordsFoundException(String message) {
		super(message);
	}

}
