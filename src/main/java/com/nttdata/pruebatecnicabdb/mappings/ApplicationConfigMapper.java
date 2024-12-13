package com.nttdata.pruebatecnicabdb.mappings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
@ConfigurationProperties("application.config")
public class ApplicationConfigMapper {
    public String TIMEZONE;
    public String ALLOWED_ORIGIN;
}