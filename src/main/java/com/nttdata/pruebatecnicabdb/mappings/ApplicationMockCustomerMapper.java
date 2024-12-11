package com.nttdata.pruebatecnicabdb.mappings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("application.mock.customer")
public class ApplicationMockCustomerMapper {
    private String documentNumber;
    private Character documentType;
    private String name;
    private String lastname;
    private String tel;
    private String address;
    private String city;
}
