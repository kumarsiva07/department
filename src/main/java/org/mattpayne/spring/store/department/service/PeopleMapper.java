package org.mattpayne.spring.store.department.service;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mattpayne.spring.store.department.domain.Department;
import org.mattpayne.spring.store.department.domain.People;
import org.mattpayne.spring.store.department.model.PeopleDTO;
import org.mattpayne.spring.store.department.repos.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PeopleMapper {

    PeopleDTO updatePeopleDTO(People people, @MappingTarget PeopleDTO peopleDTO);

    @AfterMapping
    default void afterUpdatePeopleDTO(People people, @MappingTarget PeopleDTO peopleDTO) {
        peopleDTO.setPeopleWorkInDepartmentss(people.getPeopleWorkInDepartmentsDepartments() == null ? null : people.getPeopleWorkInDepartmentsDepartments().stream()
                .map(department -> department.getId())
                .collect(Collectors.toList()));
    }

    @Mapping(target = "id", ignore = true)
    People updatePeople(PeopleDTO peopleDTO, @MappingTarget People people,
            @Context DepartmentRepository departmentRepository);

    @AfterMapping
    default void afterUpdatePeople(PeopleDTO peopleDTO, @MappingTarget People people,
            @Context DepartmentRepository departmentRepository) {
        if (peopleDTO.getPeopleWorkInDepartmentss() != null) {
            final List<Department> peopleWorkInDepartmentss = departmentRepository.findAllById(peopleDTO.getPeopleWorkInDepartmentss());
            if (peopleWorkInDepartmentss.size() != peopleDTO.getPeopleWorkInDepartmentss().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of peopleWorkInDepartmentss not found");
            }
            people.setPeopleWorkInDepartmentsDepartments(peopleWorkInDepartmentss.stream().collect(Collectors.toSet()));
        }
    }

}
