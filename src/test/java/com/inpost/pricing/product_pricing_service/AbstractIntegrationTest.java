package com.inpost.pricing.product_pricing_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureJson
@ActiveProfiles("test")
abstract class AbstractIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    protected <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType,
                                                  Object... urlVariables) {

        try {
            String requestBody = objectMapper.writeValueAsString(request);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            return restTemplate.postForEntity(url, entity, responseType, urlVariables);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Object is not processable", e);
        }
    }
}
