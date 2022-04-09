package org.mattpayne.spring.store.department.service;

import org.mattpayne.spring.store.department.model.FakeDTO;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.mattpayne.spring.store.department.repos.WorkLogRepository;
import org.springframework.stereotype.Service;

@Service
public class FakeDataService {
    private final WorkLogRepository workLogRepository;
    private final PeopleRepository peopleRepository;
    private final WorkLogMapper workLogMapper;

    public FakeDataService(final WorkLogRepository workLogRepository,
                          final PeopleRepository peopleRepository, final WorkLogMapper workLogMapper) {
        this.workLogRepository = workLogRepository;
        this.peopleRepository = peopleRepository;
        this.workLogMapper = workLogMapper;
    }

    public FakeDTO createFakeDatasetOne() {
        FakeDTO fakeDto=new FakeDTO();
        fakeDto.setStatus("Fake data not yet created, but it will be.");
        return fakeDto;
    }
}
