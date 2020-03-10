/**
 * 
 */
package com.star.sud.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.star.sud.employee.exception.EmployeeCreationException;
import com.star.sud.employee.exception.NoRecordsFoundException;
import com.star.sud.employee.exception.RequestParamException;
import com.star.sud.exception.dto.ExceptionResponse;

/**
 * @author sudarshan
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExcetionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(NoRecordsFoundException.class)
	public final ResponseEntity<Object> handleEmployeeNotFoundException(NoRecordsFoundException ex, WebRequest request)
			throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(EmployeeCreationException.class)
	public final ResponseEntity<Object> handleEmployeeCreationFoundException(EmployeeCreationException ex,
			WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(RequestParamException.class)
	public final ResponseEntity<Object> handleRequestParamException(RequestParamException ex, WebRequest request)
			throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder sb = new StringBuilder();
		BindingResult bindingResult = ex.getBindingResult();
		for (FieldError error : bindingResult.getFieldErrors())
			sb.append(error.getDefaultMessage() + ", ");

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Filed", sb.toString());

		// ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
		// "Validation Filed", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
