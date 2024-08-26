package br.com.samiac.emailservice.model.entity.attachment;

public interface Attachment {
	byte[] parse();
	String getContentType();
}
