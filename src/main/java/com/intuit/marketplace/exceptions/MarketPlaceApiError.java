package com.intuit.marketplace.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MarketPlaceApiError {
	   private HttpStatus status;
	   public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String debugMessage;
	   //private List<ApiSubError> subErrors;

	   private MarketPlaceApiError() {
	       timestamp = LocalDateTime.now();
	   }

	   MarketPlaceApiError(HttpStatus status) {
	       this();
	       this.status = status;
	   }

	   MarketPlaceApiError(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = "Unexpected error";
	       this.debugMessage = ex.getLocalizedMessage();
	   }

	   MarketPlaceApiError(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }
}
