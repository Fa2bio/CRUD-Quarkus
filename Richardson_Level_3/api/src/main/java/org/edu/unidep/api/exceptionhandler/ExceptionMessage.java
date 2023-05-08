package org.edu.unidep.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ExceptionMessage {

	@Schema(example = "2007-12-03T10:15:30")
	private LocalDateTime timeStamp;
	
	@Schema(example = "ConstraintViolationException")
	private String type;
	
	@Schema(example = "As seguintes propriedades possuem valores inválidos")
	private String userMessage;
	
	private Set<FieldError> fieldErrors;
	
	public static class FieldError{
		
		@Schema(example = "Cpf")
		private String nameField;
		
		@Schema(example = "O CPF informado deve ser válido de acordo com as normas da Receita Federal Brasileira.")
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
