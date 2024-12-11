package com.nttdata.pruebatecnicabdb.mappings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("application.messages")
public class ApplicationMessagesMapper {
    public String INVALID_NUMBER;
    public String CUSTOMER_NOT_FOUND;
    public String CUSTOMER_ERROR;
}
