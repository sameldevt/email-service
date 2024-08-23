package br.com.samiac.emailservice.model.entity;

import br.com.samiac.emailservice.pdf.PdfContent;
import br.com.samiac.emailservice.pdf.PdfParser;

public class PdfAttachment implements Attachment{
	private final String CONTENT_TYPE = "application/pdf";
	private PdfContent content;
	
	@Override
	public byte[] parse() {
		PdfParser parser = new PdfParser(content);
		return parser.parse().toByteArray();
	}

	@Override
	public String getContentType() {
		return this.CONTENT_TYPE;
	}

}
