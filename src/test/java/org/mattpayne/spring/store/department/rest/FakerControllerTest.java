package org.mattpayne.spring.store.department.rest;

import org.junit.jupiter.api.Test;
import org.mattpayne.spring.store.department.config.BaseIT;
import org.mattpayne.spring.store.department.model.ErrorResponse;
import org.mattpayne.spring.store.department.model.FakeDTO;
import org.mattpayne.spring.store.department.model.PeopleDTO;
import org.mattpayne.spring.store.department.model.SimplePage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FakerControllerTest extends BaseIT {

    @Test
    public void fakers_success() {
        final HttpEntity<String> request = new HttpEntity<>(headers());
        final ResponseEntity<FakeDTO> response = restTemplate.exchange(
                "/api/fakers", HttpMethod.POST, request, FakeDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        FakeDTO body = response.getBody(); // Set a breakpoint here to see the data generated
        // Since there are five values in the Departments enum
        assertEquals(5, departmentRepository.count());
    }

    // Finer grained unit tests would be good.  But here's a quick attempt at one and done
    @Test
    public void getHoursReport() {
        final HttpEntity<String> request = new HttpEntity<>(headers());
        final ResponseEntity<FakeDTO> response = restTemplate.exchange(
                "/api/fakers", HttpMethod.POST, request, FakeDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        FakeDTO body = response.getBody(); // Set a breakpoint here to see the data generated
        // Since there are five values in the Departments enum
        assertEquals(5, departmentRepository.count());
    }


}
