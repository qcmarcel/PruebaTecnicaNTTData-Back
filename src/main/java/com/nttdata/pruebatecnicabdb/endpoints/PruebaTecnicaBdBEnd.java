package com.nttdata.pruebatecnicabdb.endpoints;

import com.nttdata.pruebatecnicabdb.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class PruebaTecnicaBdBEnd {

    private final CustomerService service;

    @GetMapping(path = "/")
    public ResponseEntity<?> index() {
        log.info(this.getClass().toString());
        Object[] listOfRestMethods = Arrays.stream(this.getClass().getDeclaredMethods()).map(Method::getName).toArray();
        return new ResponseEntity<>(listOfRestMethods, HttpStatus.OK);
    }

    @GetMapping(path = "/customer/{documentType}/{documentNumber}")
    public ResponseEntity<?> findCustomerByDocumentNumberAndType(@PathVariable String documentNumber, @PathVariable Character documentType){
        try {
            return service.findByDocumentNumberAndType(documentNumber, documentType);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<?> findCustomerByDocumentNumberAndType(@RequestParam Map<String, String> queryParams){
        try {
            return service.findBy(queryParams);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

}
