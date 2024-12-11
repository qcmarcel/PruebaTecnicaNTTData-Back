package com.nttdata.pruebatecnicabdb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PruebaTecnicaBdBApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void portIsInUse() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String port = "8090";
        String url = "http://localhost:%s/".formatted(port);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
