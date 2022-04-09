package org.mattpayne.spring.store.department.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import org.mattpayne.spring.store.department.model.SimplePage;
import org.mattpayne.spring.store.department.model.WorkLogDTO;
import org.mattpayne.spring.store.department.service.WorkLogService;
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
@RequestMapping(value = "/api/workLogs", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkLogController {

    private final WorkLogService workLogService;

    public WorkLogController(final WorkLogService workLogService) {
        this.workLogService = workLogService;
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
    public ResponseEntity<SimplePage<WorkLogDTO>> getAllWorkLogs(
            @Parameter(hidden = true) @SortDefault(sort = "id") @PageableDefault(size = 20) final Pageable pageable) {
        return ResponseEntity.ok(workLogService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkLogDTO> getWorkLog(@PathVariable final Long id) {
        return ResponseEntity.ok(workLogService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createWorkLog(@RequestBody @Valid final WorkLogDTO workLogDTO) {
        return new ResponseEntity<>(workLogService.create(workLogDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWorkLog(@PathVariable final Long id,
            @RequestBody @Valid final WorkLogDTO workLogDTO) {
        workLogService.update(id, workLogDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkLog(@PathVariable final Long id) {
        workLogService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
