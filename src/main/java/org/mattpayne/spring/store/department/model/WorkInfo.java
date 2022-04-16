package org.mattpayne.spring.store.department.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkInfo {
  private Long id;
  private String firstName, lastName, name;
  private Float hoursWorked;
}
