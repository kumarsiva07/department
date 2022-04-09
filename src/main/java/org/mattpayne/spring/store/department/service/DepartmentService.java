package org.mattpayne.spring.store.department.service;

import java.util.List;
import java.util.stream.Collectors;
import org.mattpayne.spring.store.department.domain.Department;
import org.mattpayne.spring.store.department.model.DepartmentDTO;
import org.mattpayne.spring.store.department.repos.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(final DepartmentRepository departmentRepository,
            final DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> departmentMapper.updateDepartmentDTO(department, new DepartmentDTO()))
                .collect(Collectors.toList());
    }

    public DepartmentDTO get(final Long id) {
        return departmentRepository.findById(id)
                .map(department -> departmentMapper.updateDepartmentDTO(department, new DepartmentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DepartmentDTO departmentDTO) {
        final Department department = new Department();
        departmentMapper.updateDepartment(departmentDTO, department);
        return departmentRepository.save(department).getId();
    }

    public void update(final Long id, final DepartmentDTO departmentDTO) {
        final Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        departmentMapper.updateDepartment(departmentDTO, department);
        departmentRepository.save(department);
    }

    public void delete(final Long id) {
        departmentRepository.deleteById(id);
    }

}
