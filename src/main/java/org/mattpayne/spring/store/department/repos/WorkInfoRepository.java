package org.mattpayne.spring.store.department.repos;

import java.util.List;

import org.mattpayne.spring.store.department.model.WorkInfo;

public interface WorkInfoRepository {
   List<WorkInfo> getCustomWorkInfo(); 
}
