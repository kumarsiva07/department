package org.mattpayne.spring.store.department.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class HoursReport {
    @Id
    private Long id;
    private String lastName;
    private String firstName;
    private String departmentName;
    private BigDecimal totalHours;
}
