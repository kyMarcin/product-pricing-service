package com.inpost.pricing.product_pricing_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
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
        HttpEntity<String> entity = createHttpEntity(request);
        return restTemplate.postForEntity(url, entity, responseType, urlVariables);
    }

    protected <T> ResponseEntity<T> putForEntity(String url, Object request, Class<T> responseType,
                                                 Object... urlVariables) {
        HttpEntity<String> entity = createHttpEntity(request);
        return restTemplate.exchange(url, HttpMethod.PUT, entity, responseType, urlVariables);
    }


    private HttpEntity<String> createHttpEntity(Object request) {
        try {
            String requestBody = objectMapper.writeValueAsString(request);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return new HttpEntity<>(requestBody, headers);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Object is not processable", e);
        }
    }
}
