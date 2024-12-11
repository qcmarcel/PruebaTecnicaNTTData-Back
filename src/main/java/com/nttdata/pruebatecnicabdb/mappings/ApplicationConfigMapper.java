package com.nttdata.pruebatecnicabdb.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
@ConfigurationProperties("application.config")
public class ApplicationConfigMapper {
    private String timezone;
}