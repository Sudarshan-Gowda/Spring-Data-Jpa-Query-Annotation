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
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestParamException extends RuntimeException {

	private static final long serialVersionUID = 5140951117853690677L;

	public RequestParamException(String message) {
		super(message);
	}

}
