package org.mattpayne.spring.store.department.service;

import org.mattpayne.spring.store.department.domain.Department;
import org.mattpayne.spring.store.department.model.DepartmentDTO;
import org.mattpayne.spring.store.department.model.Departments;
import org.mattpayne.spring.store.department.model.FakeDTO;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.mattpayne.spring.store.department.repos.WorkLogRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<DepartmentDTO> listDepartmentDtos = new ArrayList<>();
        List<Departments> departments = Arrays.asList(Departments.CLOTHING, Departments.FURNITURE, Departments.HARDWARE, Departments
                .HOME_APPLIANCES, Departments.TOYS);
        for (Departments department: departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepartment(department);
            departmentDTO.setId(0L); // since JPA will set it for us.
            departmentDTO.setName(String.format("%s department", department));
            Long id = departmentService.create(departmentDTO);
            departmentDTO.setId(id);
            listDepartmentDtos.add(departmentDTO);
        }
        return listDepartmentDtos;
    }
}
