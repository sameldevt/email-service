package br.com.samiac.emailservice.model.entity;

public interface Attachment {
	byte[] parse();
	String getContentType();
}
