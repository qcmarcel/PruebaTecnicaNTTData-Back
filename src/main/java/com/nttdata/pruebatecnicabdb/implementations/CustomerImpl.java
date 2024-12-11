package com.nttdata.pruebatecnicabdb.implementations;

import com.nttdata.pruebatecnicabdb.mappings.ApplicationMessagesMapper;
import com.nttdata.pruebatecnicabdb.mappings.ApplicationMockCustomerMapper;
import com.nttdata.pruebatecnicabdb.services.CustomerService;
import com.nttdata.pruebatecnicabdb.types.CustomerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CustomerImpl implements CustomerService {


    private final ApplicationMockCustomerMapper mockCustomer;
    private final ApplicationMessagesMapper messages;
    Map<String, String> response = new HashMap<>();
    public final Map<String, String> NOT_FOUND_MESSAGE (){
        response.clear();
        response.put("messages", messages.CUSTOMER_NOT_FOUND);
        return response;
    }
    public final Map<String, String> ERROR_MESSAGE(){
        response.clear();
        response.put("message", messages.CUSTOMER_ERROR);
        return response;
    }

    public CustomerImpl(ApplicationMockCustomerMapper mockCustomer, ApplicationMessagesMapper messages) {
        this.mockCustomer = mockCustomer;
        this.messages = messages;
    }

    @Override
    public ResponseEntity<?> findByDocumentNumberAndType(String documentNumber, Character documentType) {
        try{
            if (!new CustomerType(documentNumber, documentType).isValidDocumentNumber()){
                response.clear();
                response.put("message", messages.INVALID_NUMBER);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (documentNumber.equals(mockCustomer.getDocumentNumber()) && documentType.equals(mockCustomer.getDocumentType())) {
                CustomerType customer = new CustomerType(mockCustomer);
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
            return new ResponseEntity<>(NOT_FOUND_MESSAGE(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> error = ERROR_MESSAGE();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> findBy(Map<String, String> queryParams) {
        try{
            if (queryParams.containsKey("id") && queryParams.containsKey("type")) {
                String documentNumber = queryParams.get("id");
                Character documentType = queryParams.get("type").charAt(0);
                return findByDocumentNumberAndType(documentNumber, documentType);
            } else {
                return new ResponseEntity<>(NOT_FOUND_MESSAGE(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> error = ERROR_MESSAGE();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
