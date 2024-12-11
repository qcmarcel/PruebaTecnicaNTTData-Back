package com.nttdata.pruebatecnicabdb.types;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum DocumentTypeEnum {
    C("Cedula de ciudadanía", "Cedula de ciudadanía", Pattern.compile("[0-9]{8,11}")),
    P("Pasaporte", "Pasaporte", Pattern.compile("[A-Z]{0,2}[0-9]{6,10}"));

    private final String value;
    private final String description;
    private final Pattern pattern;

    DocumentTypeEnum(String value, String description, Pattern pattern) {
        this.value = value;
        this.description = description;
        this.pattern = pattern;
    }

}
