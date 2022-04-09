package org.mattpayne.spring.store.department.service;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import com.github.javafaker.Name;
import org.mattpayne.spring.store.department.domain.Department;
import org.mattpayne.spring.store.department.model.DepartmentDTO;
import org.mattpayne.spring.store.department.model.Departments;
import org.mattpayne.spring.store.department.model.FakeDTO;
import org.mattpayne.spring.store.department.model.PeopleDTO;
import org.mattpayne.spring.store.department.repos.PeopleRepository;
import org.mattpayne.spring.store.department.repos.WorkLogRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeDataService {

    private final DepartmentService departmentService;
    private final PeopleService peopleService;
    private final Faker faker;
    private int personNumber;

    public FakeDataService(DepartmentService ds, PeopleService ps) {
        this.departmentService = ds;
        this.peopleService = ps;
        this.faker = new Faker();
        this.personNumber=0;
    }

    public FakeDTO createFakeDatasetOne() {
        List<DepartmentDTO> departmentsList = fillDepartmentTable();
        List<PeopleDTO> peopleList = fillPeopleTable(departmentsList, 3);
        FakeDTO fakeDto=new FakeDTO();
        fakeDto.setDepartments(departmentsList);
        fakeDto.setPeople(peopleList);
        return fakeDto;
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
            person.setEmail(String.format("staff%d@departmentStore.com", personNumber));
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
