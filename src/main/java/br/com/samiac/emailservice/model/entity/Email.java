package br.com.samiac.emailservice.model.entity;

public class Email {
	private String recipient;
	private String subject;
	private EmailBody body;
	
	public String getRecipient() {
		return recipient;
	}
	public String getSubject() {
		return subject;
	}
	public EmailBody getBody() {
		return body;
	}
}
