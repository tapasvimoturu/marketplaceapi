package com.intuit.marketplace.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler{

	 @Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	       String error = "Malformed JSON request";
	       return buildResponseEntity(new MarketPlaceApiError(HttpStatus.BAD_REQUEST, error, ex));
	   }

	   private ResponseEntity<Object> buildResponseEntity(MarketPlaceApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	   
	   @ExceptionHandler(APIEntityNotFoundException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(
			   APIEntityNotFoundException ex) {
		   MarketPlaceApiError apiError = new MarketPlaceApiError(HttpStatus.NOT_FOUND);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
	   
	   @ExceptionHandler(MandatoryFieldNotFoundException.class)
	   protected ResponseEntity<Object> handleMandatoryFieldNotFound(
			   MandatoryFieldNotFoundException ex) {
		   MarketPlaceApiError apiError = new MarketPlaceApiError(HttpStatus.NOT_FOUND);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
}
