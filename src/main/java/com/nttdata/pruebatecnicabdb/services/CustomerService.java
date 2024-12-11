package com.nttdata.pruebatecnicabdb.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CustomerService {

    ResponseEntity<?> findByDocumentNumberAndType(String documentNumber, Character documentType);

    ResponseEntity<?> findBy(Map<String, String> queryParams);
}
