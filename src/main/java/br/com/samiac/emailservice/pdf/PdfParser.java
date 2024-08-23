package br.com.samiac.emailservice.pdf;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.samiac.emailservice.model.entity.Attachment;

public class PdfParser {
	 private static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	    private static final Font HEADER_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	    private static final Font CONTENT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12);

	    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDateTime dateTime = LocalDateTime.now();
    
    private final PdfContent pdfContent;
    
    public PdfParser(PdfContent pdfContent) {
    	this.pdfContent = pdfContent;
    }

    public ByteArrayOutputStream parse() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            
            addTitlePage(document);
            addContent(document);
            addTable(document);
            
            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar documento PDF: " + e.getMessage(), e);
        }

        return outputStream;
    }

    private void addTitlePage(Document document) throws DocumentException {
        String reportMessage = generateReportMessage();
        document.add(createParagraph(pdfContent.getTitle(), TITLE_FONT));
        document.add(createParagraph(reportMessage, CONTENT_FONT));
    }

    private void addContent(Document document) throws DocumentException {
        document.add(createEmptyLine());
        document.add(createParagraph(pdfContent.getContent(), CONTENT_FONT));
    }

    private void addTable(Document document) throws DocumentException {
        document.add(createEmptyLine());
        document.add(createParagraph(pdfContent.getTable().getTitle(), HEADER_FONT));
        
        PdfPTable table = new PdfPTable(pdfContent.getTable().getHeaders().length);
        table.setWidthPercentage(100);

        addTableHeader(table);
        addTableContent(table);

        document.add(table);
    }

    private void addTableHeader(PdfPTable table) {
        for (String header : pdfContent.getTable().getHeaders()) {
            PdfPCell cell = new PdfPCell(new Phrase(header, HEADER_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        table.setHeaderRows(1);
    }

    private void addTableContent(PdfPTable table) {
    	TableContent content = pdfContent.getTable().getContent();
        for (String cell : content.getCells()) {
            table.addCell(createTableCell(cell));
        }
    }

    private PdfPCell createTableCell(String text) {
        return new PdfPCell(new Phrase(text, CONTENT_FONT));
    }

    private Paragraph createParagraph(String text, Font font) {
        return new Paragraph(text, font);
    }

    private Paragraph createEmptyLine() {
        return new Paragraph(" ");
    }

    private String generateReportMessage() {
        return "Relatório gerado às " + dateTime.format(TIME_FORMATTER) + " de " + dateTime.format(DATE_FORMATTER);
    }
}
