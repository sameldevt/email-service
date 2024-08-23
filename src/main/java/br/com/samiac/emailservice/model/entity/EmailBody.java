package br.com.samiac.emailservice.model.entity;

public class EmailBody {
	protected String message;
	private Attachment attachment;
	
	public String getMessage() {
		return message;
	}
	public Attachment getAttachment() {
		return attachment;
	}
}
