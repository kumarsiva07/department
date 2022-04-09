package org.mattpayne.spring.store.department.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mattpayne.spring.store.department.config.BaseIT;
import org.mattpayne.spring.store.department.model.DepartmentDTO;
import org.mattpayne.spring.store.department.model.ErrorResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;


public class DepartmentControllerTest extends BaseIT {

    @Test
    @Sql("/data/departmentData.sql")
    public void getAllDepartments_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<List<DepartmentDTO>> response = restTemplate.exchange(
                "/api/departments", HttpMethod.GET, request, new ParameterizedTypeReference<List<DepartmentDTO>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals((long)1100, response.getBody().get(0).getId());
    }

    @Test
    @Sql("/data/departmentData.sql")
    public void getDepartment_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<DepartmentDTO> response = restTemplate.exchange(
                "/api/departments/1100", HttpMethod.GET, request, DepartmentDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cras sed interdum...", response.getBody().getName());
    }

    @Test
    public void getDepartment_notFound() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/departments/1766", HttpMethod.GET, request, ErrorResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("ResponseStatusException", response.getBody().getException());
    }

    @Test
    public void createDepartment_success() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/departmentDTORequest.json"), headers());
        final ResponseEntity<Long> response = restTemplate.exchange(
                "/api/departments", HttpMethod.POST, request, Long.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, departmentRepository.count());
    }

    @Test
    public void createDepartment_missingField() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/departmentDTORequest_missingField.json"), headers());
        final ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/departments", HttpMethod.POST, request, ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("MethodArgumentNotValidException", response.getBody().getException());
        assertEquals("department", response.getBody().getFieldErrors().get(0).getField());
    }

    @Test
    @Sql("/data/departmentData.sql")
    public void updateDepartment_success() {
        final HttpEntity<String> request = new HttpEntity<>(readResource("/requests/departmentDTORequest.json"), headers());
        final ResponseEntity<Void> response = restTemplate.exchange(
                "/api/departments/1100", HttpMethod.PUT, request, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Donec ac nibh...", departmentRepository.findById((long)1100).get().getName());
    }

    @Test
    @Sql("/data/departmentData.sql")
    public void deleteDepartment_success() {
        final HttpEntity<String> request = new HttpEntity<>(null, headers());
        final ResponseEntity<Void> response = restTemplate.exchange(
                "/api/departments/1100", HttpMethod.DELETE, request, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(0, departmentRepository.count());
    }

}
