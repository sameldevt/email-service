package br.com.samiac.emailservice.config;

import br.com.samiac.emailservice.model.entity.attachment.Attachment;
import br.com.samiac.emailservice.model.entity.EmailDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Attachment.class, new EmailDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
