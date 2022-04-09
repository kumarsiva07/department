package org.mattpayne.spring.store.department.service;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mattpayne.spring.store.department.domain.People;
import org.mattpayne.spring.store.department.domain.WorkLog;
import org.mattpayne.spring.store.department.model.WorkLogDTO;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface WorkLogMapper {

    @Mapping(target = "whenPeopleWorked", ignore = true)
    WorkLogDTO updateWorkLogDTO(WorkLog workLog, @MappingTarget WorkLogDTO workLogDTO);

    @AfterMapping
    default void afterUpdateWorkLogDTO(WorkLog workLog, @MappingTarget WorkLogDTO workLogDTO) {
        workLogDTO.setWhenPeopleWorked(workLog.getWhenPeopleWorked() == null ? null : workLog.getWhenPeopleWorked().getId());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "whenPeopleWorked", ignore = true)
    WorkLog updateWorkLog(WorkLogDTO workLogDTO, @MappingTarget WorkLog workLog,
            @Context PeopleRepository peopleRepository);

    @AfterMapping
    default void afterUpdateWorkLog(WorkLogDTO workLogDTO, @MappingTarget WorkLog workLog,
            @Context PeopleRepository peopleRepository) {
        if (workLogDTO.getWhenPeopleWorked() != null && (workLog.getWhenPeopleWorked() == null || !workLog.getWhenPeopleWorked().getId().equals(workLogDTO.getWhenPeopleWorked()))) {
            final People whenPeopleWorked = peopleRepository.findById(workLogDTO.getWhenPeopleWorked())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "whenPeopleWorked not found"));
            workLog.setWhenPeopleWorked(whenPeopleWorked);
        }
    }

}
