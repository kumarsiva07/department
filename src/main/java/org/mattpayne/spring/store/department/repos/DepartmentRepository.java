package org.mattpayne.spring.store.department.repos;

import org.mattpayne.spring.store.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
