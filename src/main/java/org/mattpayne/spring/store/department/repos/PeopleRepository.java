package org.mattpayne.spring.store.department.repos;

import org.mattpayne.spring.store.department.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeopleRepository extends JpaRepository<People, Long> {
}
