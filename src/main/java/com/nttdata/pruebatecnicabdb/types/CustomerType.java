package com.nttdata.pruebatecnicabdb.types;

import com.nttdata.pruebatecnicabdb.mappings.ApplicationMockCustomerMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerType implements Serializable {
    private String documentNumber;
    private String documentType;
    private String name;
    private String lastname;
    private String tel;
    private String address;
    private String city;

    public CustomerType(String documentNumber, Character documentType) {
        this.documentNumber = documentNumber;
        this.documentType = documentType.toString();
    }

    public boolean isValidDocumentNumber() {
        return DocumentTypeEnum.valueOf("%s".formatted(this.documentType.toUpperCase().charAt(0))).getPattern().matcher(this.documentNumber).matches();
    }

    public void setDocumentType(String documentType) {
        this.documentType = DocumentTypeEnum.valueOf("%s".formatted(documentType.toUpperCase().charAt(0))).getValue();
    }

    public CustomerType(ApplicationMockCustomerMapper mockCustomer) {
        this.documentNumber = mockCustomer.getDocumentNumber();
        setDocumentType(mockCustomer.getDocumentType().toString());
        this.name = mockCustomer.getName();
        this.lastname = mockCustomer.getLastname();
        this.tel = mockCustomer.getTel();
        this.address = mockCustomer.getAddress();
        this.city = mockCustomer.getCity();
    }
}
