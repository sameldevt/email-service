package br.com.samiac.emailservice.pdf;

import java.util.List;

public class Table {
	private String title;
	private String[] headers; 
	private TableContent content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public TableContent getContent() {
		return content;
	}
	public void setContent(TableContent content) {
		this.content = content;
	} 

	
}
