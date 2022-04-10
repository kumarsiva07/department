package org.mattpayne.spring.store.department.service;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import com.github.javafaker.Name;
import org.mattpayne.spring.store.department.domain.Department;
import org.mattpayne.spring.store.department.model.*;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.mattpayne.spring.store.department.repos.WorkLogRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FakeDataService {

    private final DepartmentService departmentService;
    private final PeopleService peopleService;
    private final WorkLogService workLogService;
    private final Faker faker;
    private int personNumber; // used in a bad & unsafe way to provide unique emails

    public FakeDataService(DepartmentService ds, PeopleService ps, WorkLogService ws) {
        this.departmentService = ds;
        this.peopleService = ps;
        this.workLogService = ws;
        this.faker = new Faker();
        this.personNumber=0;
    }

    public FakeDTO createFakeDatasetOne() {
        List<DepartmentDTO> departmentsList;
        departmentsList = departmentService.findAll();
        if (departmentsList.isEmpty()) {
            departmentsList = fillDepartmentTable();
        }
        List<PeopleDTO> peopleList = fillPeopleTable(departmentsList, 3);
        List<WorkLogDTO> workedShiftList = fillWorkLog(peopleList, 2);
        FakeDTO fakeDto=new FakeDTO();
        fakeDto.setDepartments(departmentsList);
        fakeDto.setPeople(peopleList);
        fakeDto.setWorkedShifts(workedShiftList);
        return fakeDto;
    }

    private List<WorkLogDTO> fillWorkLog(List<PeopleDTO> peopleList, int numLogsPerPerson) {
        List<WorkLogDTO> workLogList = new ArrayList<>();
        peopleList.forEach(person -> {
            List<WorkLogDTO> shifts = fakeShifts(person, numLogsPerPerson);
            workLogList.addAll(shifts);
        });
        return workLogList;
    }

    private List<WorkLogDTO> fakeShifts(PeopleDTO person, int numLogsPerPerson) {
        List<WorkLogDTO> shifts=new ArrayList<>();
        for (int s=0; s < numLogsPerPerson; s++) {
            shifts.add(fakeShift(person));
        }
        return shifts;
    }

    private WorkLogDTO fakeShift(PeopleDTO person) {
        WorkLogDTO shift=new WorkLogDTO();
        Long departmentId = person.getPeopleWorkInDepartmentss().get(0);
        DepartmentDTO department = departmentService.get(departmentId);
        shift.setDepartment(department.getDepartment());
        shift.setUuid(UUID.randomUUID().toString());
        LocalTime currentTime = LocalTime.now();
        shift.setStartTime(currentTime);
        shift.setStopTime(currentTime.plusHours(8));
        LocalDate workDay=LocalDate.now().minusDays(ThreadLocalRandom.current().nextInt(0, 6 + 1));
        shift.setWorkDay(workDay);
        shift.setWhenPeopleWorked(person.getId()); // join those tables together!
        Long id = workLogService.create(shift);
        shift.setId(id);
        return shift;
    }


    private List<PeopleDTO> fillPeopleTable(List<DepartmentDTO> departmentsList, int numPeoplePerDepartment) {
        List<PeopleDTO> peopleDtoList = new ArrayList<>();
        departmentsList.forEach(departmentDto -> {
            List<PeopleDTO> people = addPeopleToDepartment(departmentDto, numPeoplePerDepartment);
            peopleDtoList.addAll(people);
        });
        return peopleDtoList;
    }

    private List<PeopleDTO> addPeopleToDepartment(DepartmentDTO departmentDto, int numPeoplePerDepartment) {
        List<PeopleDTO> people=new ArrayList<>();
        for (int p=0; p < numPeoplePerDepartment; p++) {
            PeopleDTO person = new PeopleDTO();
            Name name = faker.name();
            person.setFirstName(name.firstName());
            person.setLastName(name.lastName());
            LocalDate hireDate=LocalDate.now().minusDays(365*ThreadLocalRandom.current().nextInt(0, 6 + 1));
            person.setHiredate(hireDate);
            person.setEmail(String.format("staff%d@departmentStore.com", personNumber++));
            IdNumber idNumber = faker.idNumber();
            String ssn = idNumber.ssnValid();
            person.setUuid(ssn + " fake UUID");
            person.setPeopleWorkInDepartmentss(Arrays.asList(departmentDto.getId()));
            try {
                Long id = this.peopleService.create(person);
                person.setId(id);
                people.add(person);
            } catch (DataIntegrityViolationException dive) {
                dive.printStackTrace();
            }
        }
        return people;
    }

    private List<DepartmentDTO> fillDepartmentTable() {
        List<DepartmentDTO> listDepartmentDtos = new ArrayList<>();
        List<Departments> departments = Arrays.asList(Departments.CLOTHING, Departments.FURNITURE, Departments.HARDWARE, Departments
                .HOME_APPLIANCES, Departments.TOYS);
        for (Departments department: departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepartment(department);
            departmentDTO.setId(0L); // since JPA will set it for us.
            departmentDTO.setName(String.format("%s department", department));
            Long id = departmentService.create(departmentDTO);
            departmentDTO.setId(id);
            listDepartmentDtos.add(departmentDTO);
        }
        return listDepartmentDtos;
    }
}
