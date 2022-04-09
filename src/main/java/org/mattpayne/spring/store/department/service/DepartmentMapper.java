package org.mattpayne.spring.store.department.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mattpayne.spring.store.department.domain.Department;
import org.mattpayne.spring.store.department.model.DepartmentDTO;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DepartmentMapper {

    DepartmentDTO updateDepartmentDTO(Department department,
            @MappingTarget DepartmentDTO departmentDTO);

    @Mapping(target = "id", ignore = true)
    Department updateDepartment(DepartmentDTO departmentDTO, @MappingTarget Department department);

}
