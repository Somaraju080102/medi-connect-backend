package com.spring.notification.message;


public class RegistrationNotification {
	
	private String to;
	
	private String body;
	
	private static final String subject = "🎉 Registration Successful — Welcome to MediConnect!";

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	

	

}
