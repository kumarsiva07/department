package org.mattpayne.spring.store.department.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.mattpayne.spring.store.department.model.WorkInfo;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@SqlResultSetMapping(
        name = "getHoursReport",
        classes = {
                @ConstructorResult(targetClass = WorkInfo.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "last_name", type = String.class),
                                @ColumnResult(name = "first_name", type = String.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "total_hours", type = Float.class)
                        }
                )
        }
)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class People {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private LocalDate hiredate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "people_work_in_departments",
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> peopleWorkInDepartmentsDepartments;

    @OneToMany(mappedBy = "whenPeopleWorked")
    private Set<WorkLog> whenPeopleWorkedWorkLogs;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
