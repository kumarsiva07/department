package org.mattpayne.spring.store.department.repos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mattpayne.spring.store.department.model.WorkInfo;

public class WorkInfoRepositoryImpl implements WorkInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkInfo> getCustomWorkInfo() {
        // TODO Auto-generated method stub
        List<WorkInfo> results = new ArrayList<>();
        System.out.println("***** entityMangager:");
        System.out.println(entityManager);
        System.out.println("^^^^ that is entityManager");
        return results;
    }
    
}
