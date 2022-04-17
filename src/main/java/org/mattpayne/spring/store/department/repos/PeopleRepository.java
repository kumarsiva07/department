package org.mattpayne.spring.store.department.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.mattpayne.spring.store.department.domain.People;

public interface PeopleRepository extends JpaRepository<People, Long>, WorkInfoRepository {
}
