package org.mattpayne.spring.store.department.rest;

import org.mattpayne.spring.store.department.model.DepartmentDTO;
import org.mattpayne.spring.store.department.model.FakeDTO;
import org.mattpayne.spring.store.department.service.FakeDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/fakers", produces = MediaType.APPLICATION_JSON_VALUE)
public class FakerController {

    private final FakeDataService fakeDataService;

    public FakerController(final FakeDataService fds) {
        this.fakeDataService = fds;
    }

    @PostMapping
    public ResponseEntity<FakeDTO> createFakeDatasetOne() {
        return new ResponseEntity<>(fakeDataService.createFakeDatasetOne(), HttpStatus.CREATED);
    }
}
