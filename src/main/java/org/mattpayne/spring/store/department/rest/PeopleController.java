package org.mattpayne.spring.store.department.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import javax.validation.Valid;
import org.mattpayne.spring.store.department.model.PeopleDTO;
import org.mattpayne.spring.store.department.model.SimplePage;
import org.mattpayne.spring.store.department.model.WorkInfo;
import org.mattpayne.spring.store.department.service.PeopleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/peoples", produces = MediaType.APPLICATION_JSON_VALUE)
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(final PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Operation(
            parameters = {
                    @Parameter(
                            name = "page",
                            in = ParameterIn.QUERY,
                            schema = @Schema(implementation = Integer.class)
                    ),
                    @Parameter(
                            name = "size",
                            in = ParameterIn.QUERY,
                            schema = @Schema(implementation = Integer.class)
                    ),
                    @Parameter(
                            name = "sort",
                            in = ParameterIn.QUERY,
                            schema = @Schema(implementation = String.class)
                    )
            }
    )
    @GetMapping
    public ResponseEntity<SimplePage<PeopleDTO>> getAllPeoples(
            @Parameter(hidden = true) @SortDefault(sort = "id") @PageableDefault(size = 20) final Pageable pageable) {
        return ResponseEntity.ok(peopleService.findAll(pageable));
    }


    @GetMapping("/workInfo")
    public ResponseEntity<List<WorkInfo>> getCustomWorkInfo() {
        return ResponseEntity.ok(peopleService.getCustomWorkInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeopleDTO> getPeople(@PathVariable final Long id) {
        return ResponseEntity.ok(peopleService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createPeople(@RequestBody @Valid final PeopleDTO peopleDTO) {
        return new ResponseEntity<>(peopleService.create(peopleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePeople(@PathVariable final Long id,
            @RequestBody @Valid final PeopleDTO peopleDTO) {
        peopleService.update(id, peopleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable final Long id) {
        peopleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
