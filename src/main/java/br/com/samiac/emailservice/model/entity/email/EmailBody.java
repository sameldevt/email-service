package br.com.samiac.emailservice.model.entity.email;

import br.com.samiac.emailservice.model.entity.attachment.Attachment;
import com.itextpdf.text.pdf.PdfDocument;

public class EmailBody {
    private final String text;
    private final Attachment attachment;

    private EmailBody(Builder builder) {
        this.text = builder.text;
        this.attachment = builder.attachment;
    }

    public String getText() {
        return text;
    }

    public PdfDocument getAttachment() {
        return attachment;
    }

    public static class Builder {
        private String text;
        private PdfDocument attachment;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder attachment(PdfDocument attachment) {
            this.attachment = attachment;
            return this;
        }

        public EmailBody build() {
            return new EmailBody(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
