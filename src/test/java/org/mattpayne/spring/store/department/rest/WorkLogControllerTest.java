package org.mattpayne.spring.store.department.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mattpayne.spring.store.department.config.BaseIT;
import org.mattpayne.spring.store.department.model.ErrorResponse;
import org.mattpayne.spring.store.department.model.SimplePage;
import org.mattpayne.spring.store.department.model.WorkLogDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;


public class WorkLogControllerTest extends BaseIT {

    @Test
    @Sql("/data/workLogData.sql")
    public void getAllWorkLogs_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<SimplePage<WorkLogDTO>> response = restTemplate.exchange(
                "/api/workLogs", HttpMethod.GET, request, new ParameterizedTypeReference<SimplePage<WorkLogDTO>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalPages());
        assertEquals((long)1200, response.getBody().getContent().get(0).getId());
    }

    @Test
    @Sql("/data/workLogData.sql")
    public void getWorkLog_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<WorkLogDTO> response = restTemplate.exchange(
                "/api/workLogs/1200", HttpMethod.GET, request, WorkLogDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cras sed interdum...", response.getBody().getUuid());
    }

    @Test
    public void getWorkLog_notFound() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/workLogs/1866", HttpMethod.GET, request, ErrorResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("ResponseStatusException", response.getBody().getException());
    }

    @Test
    public void createWorkLog_success() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/workLogDTORequest.json"), headers());
        final ResponseEntity<Long> response = restTemplate.exchange(
                "/api/workLogs", HttpMethod.POST, request, Long.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, workLogRepository.count());
    }

    @Test
    public void createWorkLog_missingField() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/workLogDTORequest_missingField.json"), headers());
        final ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/workLogs", HttpMethod.POST, request, ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("MethodArgumentNotValidException", response.getBody().getException());
        assertEquals("uuid", response.getBody().getFieldErrors().get(0).getField());
    }

    @Test
    @Sql("/data/workLogData.sql")
    public void updateWorkLog_success() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/workLogDTORequest.json"), headers());
        final ResponseEntity<Void> response = restTemplate.exchange(
                "/api/workLogs/1200", HttpMethod.PUT, request, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Donec ac nibh...", workLogRepository.findById((long)1200).get().getUuid());
    }

    @Test
    @Sql("/data/workLogData.sql")
    public void deleteWorkLog_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<Void> response = restTemplate.exchange(
                "/api/workLogs/1200", HttpMethod.DELETE, request, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(0, workLogRepository.count());
    }

}
