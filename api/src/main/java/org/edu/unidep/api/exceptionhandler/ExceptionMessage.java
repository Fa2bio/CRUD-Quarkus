package org.edu.unidep.api.exceptionhandler;

import java.time.LocalDateTime;

public class ExceptionMessage {

	private LocalDateTime timeStamp;
	
	private String type;
	
	private String userMessage;

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
	
	
}
