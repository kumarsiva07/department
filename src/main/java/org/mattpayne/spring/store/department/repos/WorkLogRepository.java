package org.mattpayne.spring.store.department.repos;

import org.mattpayne.spring.store.department.domain.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
}
