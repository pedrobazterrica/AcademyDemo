package vivere.academia.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    //Added this bean to have only one objectmapper instanciated
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}