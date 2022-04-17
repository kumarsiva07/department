package org.mattpayne.spring.store.department.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mattpayne.spring.store.department.config.BaseIT;
import org.mattpayne.spring.store.department.model.ErrorResponse;
import org.mattpayne.spring.store.department.model.PeopleDTO;
import org.mattpayne.spring.store.department.model.SimplePage;
import org.mattpayne.spring.store.department.model.WorkInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;


public class PeopleControllerTest extends BaseIT {

    @Test
    @Sql("/data/peopleData.sql")
    public void getAllPeoples_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<SimplePage<PeopleDTO>> response = restTemplate.exchange(
                "/api/peoples", HttpMethod.GET, request, new ParameterizedTypeReference<SimplePage<PeopleDTO>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalPages());
        assertEquals((long)1000, response.getBody().getContent().get(0).getId());
    }

    @Test
    @Sql("/data/peopleData.sql")
    public void getPeople_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<PeopleDTO> response = restTemplate.exchange(
                "/api/peoples/1000", HttpMethod.GET, request, PeopleDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cras sed interdum...", response.getBody().getUuid());
    }

    @Test
    public void getCustomWorkInfo_success() {
        System.out.println("\n\n********** getCustomWorkInfo_success\n\n");
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<List<WorkInfo>> response = restTemplate.exchange(
            "/api/peoples/workInfo", HttpMethod.GET, request, 
                new ParameterizedTypeReference<List<WorkInfo>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        assertEquals(4, 5, "message: ");
    }

    @Test
    public void getPeople_notFound() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/peoples/1666", HttpMethod.GET, request, ErrorResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("ResponseStatusException", response.getBody().getException());
    }

    @Test
    public void createPeople_success() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/peopleDTORequest.json"), headers());
        final ResponseEntity<Long> response = restTemplate.exchange(
                "/api/peoples", HttpMethod.POST, request, Long.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, peopleRepository.count());
    }

    @Test
    public void createPeople_missingField() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/peopleDTORequest_missingField.json"), headers());
        final ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/peoples", HttpMethod.POST, request, ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("MethodArgumentNotValidException", response.getBody().getException());
        assertEquals("uuid", response.getBody().getFieldErrors().get(0).getField());
    }

    @Test
    @Sql("/data/peopleData.sql")
    public void updatePeople_success() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/peopleDTORequest.json"), headers());
        final ResponseEntity<Void> response = restTemplate.exchange(
                "/api/peoples/1000", HttpMethod.PUT, request, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Donec ac nibh...", peopleRepository.findById((long)1000).get().getUuid());
    }

    @Test
    @Sql("/data/peopleData.sql")
    public void deletePeople_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<Void> response = restTemplate.exchange(
                "/api/peoples/1000", HttpMethod.DELETE, request, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(0, peopleRepository.count());
    }

}
