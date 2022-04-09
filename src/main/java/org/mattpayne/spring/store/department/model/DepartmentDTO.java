package org.mattpayne.spring.store.department.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartmentDTO {

    private Long id;

    @NotNull
    private Departments department;

    @NotNull
    @Size(max = 255)
    private String name;

}
