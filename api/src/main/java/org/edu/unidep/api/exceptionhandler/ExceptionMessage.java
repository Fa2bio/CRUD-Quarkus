package org.edu.unidep.api.exceptionhandler;

import java.time.LocalDateTime;

public class ExceptionMessage {

	private LocalDateTime timestamp;
	
	private String type;
	
	private String userMessage;

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
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
	
	
}
