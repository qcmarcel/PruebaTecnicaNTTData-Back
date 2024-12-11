package com.nttdata.pruebatecnicabdb.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.pruebatecnicabdb.mappings.ApplicationConfigMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class ObjectMappingConfiguration {

    public static final String DEFAULT_TIMEZONE = "GMT-5:00";
    private final ApplicationConfigMapper props;

    public ObjectMappingConfiguration(ApplicationConfigMapper props) {
        this.props = props;
    }

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String timezone = props.TIMEZONE;
        if (timezone == null){
            timezone = DEFAULT_TIMEZONE;
        }
        mapper.setTimeZone(TimeZone.getTimeZone(timezone));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

}
