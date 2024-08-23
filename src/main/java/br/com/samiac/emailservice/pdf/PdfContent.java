package br.com.samiac.emailservice.pdf;

public class PdfContent{
	private String content;
	private String title;
	private Table table;
	
	public PdfContent(String content, String title, Table table) {
		this.content = content;
		this.title = title;
		this.table = table;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
