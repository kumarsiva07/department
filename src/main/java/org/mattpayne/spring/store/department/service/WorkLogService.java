package org.mattpayne.spring.store.department.service;

import java.util.stream.Collectors;
import org.mattpayne.spring.store.department.domain.WorkLog;
import org.mattpayne.spring.store.department.model.SimplePage;
import org.mattpayne.spring.store.department.model.WorkLogDTO;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.mattpayne.spring.store.department.repos.WorkLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class WorkLogService {

    private final WorkLogRepository workLogRepository;
    private final PeopleRepository peopleRepository;
    private final WorkLogMapper workLogMapper;

    public WorkLogService(final WorkLogRepository workLogRepository,
            final PeopleRepository peopleRepository, final WorkLogMapper workLogMapper) {
        this.workLogRepository = workLogRepository;
        this.peopleRepository = peopleRepository;
        this.workLogMapper = workLogMapper;
    }

    public SimplePage<WorkLogDTO> findAll(final Pageable pageable) {
        final Page<WorkLog> page = workLogRepository.findAll(pageable);
        return new SimplePage<>(page.getContent()
                .stream()
                .map(workLog -> workLogMapper.updateWorkLogDTO(workLog, new WorkLogDTO()))
                .collect(Collectors.toList()),
                page.getTotalElements(), pageable);
    }

    public WorkLogDTO get(final Long id) {
        return workLogRepository.findById(id)
                .map(workLog -> workLogMapper.updateWorkLogDTO(workLog, new WorkLogDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final WorkLogDTO workLogDTO) {
        final WorkLog workLog = new WorkLog();
        workLogMapper.updateWorkLog(workLogDTO, workLog, peopleRepository);
        return workLogRepository.save(workLog).getId();
    }

    public void update(final Long id, final WorkLogDTO workLogDTO) {
        final WorkLog workLog = workLogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        workLogMapper.updateWorkLog(workLogDTO, workLog, peopleRepository);
        workLogRepository.save(workLog);
    }

    public void delete(final Long id) {
        workLogRepository.deleteById(id);
    }

}
