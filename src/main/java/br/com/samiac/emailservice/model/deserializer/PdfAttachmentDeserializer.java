package br.com.samiac.emailservice.model.deserializer;

import br.com.samiac.emailservice.model.entity.attachment.PdfAttachment;
import br.com.samiac.emailservice.pdf.TableContent;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfAttachmentDeserializer extends JsonDeserializer<PdfAttachment> {

    @Override
    public PdfAttachment deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);

        JsonNode attachment = node.get("attachment");

        String content = node.get("content").asText();
        String title = node.get("title").asText();

        JsonNode table = node.get("table");
        String tableTitle = table.get("title").asText();
        Iterator<JsonNode> tableHeaders = table.get("headers").elements();
        List<String> headers = new ArrayList<>();
        while(tableHeaders.hasNext()){
            JsonNode header = tableHeaders.next();
            String headerValue = header.asText();
            headers.add(headerValue);
        }

        List<TableContent> tableContentList = new ArrayList<>();
        Iterator<JsonNode> tableContent = table.get("content").elements();
        ObjectMapper objectMapper = new ObjectMapper();

        while (tableContent.hasNext()) {
            JsonNode node1 = tableContent.next();
            TableContent tableInfo = objectMapper.treeToValue(node, TableContent.class);
            tableContentList.add(tableInfo);
        }

        return new PdfAttachment();
    }
}
