package org.edu.unidep.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.Set;

public class ExceptionMessage {

	private LocalDateTime timeStamp;
	
	private String type;
	
	private String userMessage;
	
	private Set<FieldError> fieldErrors;
	
	public static class FieldError{
		
		private String nameField;
		private String userMessage;
		
		public FieldError(String nameField, String userMessage) {
			this.nameField = nameField;
			this.userMessage = userMessage;
		}
		
		public String getNameField() {
			return nameField;
		}
		public void setNameField(String nameField) {
			this.nameField = nameField;
		}
		public String getUserMessage() {
			return userMessage;
		}
		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}
		
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public Set<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Set<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
}
