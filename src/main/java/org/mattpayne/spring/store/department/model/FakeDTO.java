package org.mattpayne.spring.store.department.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeDTO {
    private List<DepartmentDTO> departments;
    private List<PeopleDTO> people;
}
