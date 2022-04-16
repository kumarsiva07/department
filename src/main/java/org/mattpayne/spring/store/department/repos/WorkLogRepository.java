package org.mattpayne.spring.store.department.repos;

import org.mattpayne.spring.store.department.domain.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
    /*
     SELECT p.id, p.last_name, p.first_name, d.name,
     sum(((datediff('ms', '1970-01-01', wl.stop_time) - datediff('ms', '1970-01-01', wl.start_time))/1000)/(60*60))
     as total_hours
from
    WORK_LOG as wl,
    people as p,
    PEOPLE_WORK_IN_DEPARTMENTS as pwd,
    department  as d
    where pwd.department_id = d.id and
    p.id = pwd.people_id and
    wl.when_people_worked_id = p.id
    group by p.id
    order by total_hours desc;
     */
}
