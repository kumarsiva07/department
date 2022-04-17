package org.mattpayne.spring.store.department.repos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mattpayne.spring.store.department.model.WorkInfo;

public class WorkInfoRepositoryImpl implements WorkInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkInfo> getCustomWorkInfo() {
        String query = "SELECT p.id, p.last_name, p.first_name, d.name, "
                + "sum(((datediff('ms', '1970-01-01', wl.stop_time) - datediff('ms', '1970-01-01', wl.start_time))/1000)/(60*60)) "
                + "as total_hours "
                + "from "
                + "WORK_LOG as wl, "
                + "people as p, "
                + "PEOPLE_WORK_IN_DEPARTMENTS as pwd, "
                + "department  as d "
                + "where pwd.department_id = d.id and "
                + "p.id = pwd.people_id and "
                + "wl.when_people_worked_id = p.id "
                + "group by p.id "
                + "order by total_hours desc; ";

        // TODO Auto-generated method stub
        List<WorkInfo> results = new ArrayList<>();
        System.out.println("***** entityMangager:");
        System.out.println(entityManager);
        System.out.println("^^^^ that is entityManager");
        Query queryResult = entityManager.createNativeQuery(query,"getHoursReport");
        List rl = queryResult.getResultList();
        System.out.println(rl);
        return results;
    }
    
}
