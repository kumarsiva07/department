package org.mattpayne.spring.store.department.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WorkLogDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String uuid;

    @NotNull
    private LocalDate workDay;

    @NotNull
    @Schema(type = "string", example = "14:30")
    private LocalTime startTime;

    @NotNull
    @Schema(type = "string", example = "18:30")
    private LocalTime stopTime;

    @NotNull
    private Departments department;

    private Long whenPeopleWorked;

}
