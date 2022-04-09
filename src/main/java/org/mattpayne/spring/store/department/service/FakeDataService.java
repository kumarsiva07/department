package org.mattpayne.spring.store.department.service;

import org.mattpayne.spring.store.department.domain.Department;
import org.mattpayne.spring.store.department.model.DepartmentDTO;
import org.mattpayne.spring.store.department.model.Departments;
import org.mattpayne.spring.store.department.model.FakeDTO;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.mattpayne.spring.store.department.repos.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeDataService {

    private final DepartmentService departmentService;

    public FakeDataService(DepartmentService ds) {
        this.departmentService = ds;
    }

    public FakeDTO createFakeDatasetOne() {
        List<DepartmentDTO> departmentsList = fillDepartmentTable();
        FakeDTO fakeDto=new FakeDTO();
        fakeDto.setStatus(String.format("Created %d departments.", departmentsList.size()));
        return fakeDto;
    }

    private List<DepartmentDTO> fillDepartmentTable() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartment(Departments.CLOTHING);
        departmentDTO.setId(0L);
        departmentDTO.setName(String.format("%s department", Departments.CLOTHING));
        Long id = departmentService.create(departmentDTO);
        departmentDTO.setId(id);
        List<DepartmentDTO> listDepartmentDtos = new ArrayList<>();
        listDepartmentDtos.add(departmentDTO);
        return listDepartmentDtos;
    }
}
