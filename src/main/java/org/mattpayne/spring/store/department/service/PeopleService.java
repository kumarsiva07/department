package org.mattpayne.spring.store.department.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.mattpayne.spring.store.department.domain.People;
import org.mattpayne.spring.store.department.model.HoursReportDTO;
import org.mattpayne.spring.store.department.model.PeopleDTO;
import org.mattpayne.spring.store.department.model.SimplePage;
import org.mattpayne.spring.store.department.repos.DepartmentRepository;
import org.mattpayne.spring.store.department.repos.HoursRepository;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final DepartmentRepository departmentRepository;
    private final PeopleMapper peopleMapper;
    private HoursRepository hoursRepository;

    public PeopleService(final PeopleRepository peopleRepository, final HoursRepository hr,
            final DepartmentRepository departmentRepository, final PeopleMapper peopleMapper) {
        this.peopleRepository = peopleRepository;
        this.hoursRepository = hr;
        this.departmentRepository = departmentRepository;
        this.peopleMapper = peopleMapper;
    }

    public SimplePage<PeopleDTO> findAll(final Pageable pageable) {
        final Page<People> page = peopleRepository.findAll(pageable);
        return new SimplePage<>(page.getContent()
                .stream()
                .map(people -> peopleMapper.updatePeopleDTO(people, new PeopleDTO()))
                .collect(Collectors.toList()),
                page.getTotalElements(), pageable);
    }
    public List<HoursReportDTO> getHoursReport(Pageable pageable) {
        // TODO: use the pageable when the data gets bigger...
        List<HoursReportDTO> hoursReport = hoursRepository.getHoursReport();
        return hoursReport;
    }

    public PeopleDTO get(final Long id) {
        return peopleRepository.findById(id)
                .map(people -> peopleMapper.updatePeopleDTO(people, new PeopleDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PeopleDTO peopleDTO) {
        final People people = new People();
        peopleMapper.updatePeople(peopleDTO, people, departmentRepository);
        return peopleRepository.save(people).getId();
    }

    public void update(final Long id, final PeopleDTO peopleDTO) {
        final People people = peopleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        peopleMapper.updatePeople(peopleDTO, people, departmentRepository);
        peopleRepository.save(people);
    }

    public void delete(final Long id) {
        peopleRepository.deleteById(id);
    }

}
