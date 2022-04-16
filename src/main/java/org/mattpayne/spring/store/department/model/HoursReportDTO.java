package org.mattpayne.spring.store.department.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HoursReportDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String departmentName;
    private BigDecimal totalHours;
}
