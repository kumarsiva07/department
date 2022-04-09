package org.mattpayne.spring.store.department.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PeopleDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String uuid;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

    private List<Long> peopleWorkInDepartmentss;

}
