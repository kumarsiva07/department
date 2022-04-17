package org.mattpayne.spring.store.department.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkInfo {
  private Long id;
  private String firstName, lastName, name;
  private Float hoursWorked;

  public String toString() {
    return String.format("%s, %s worked %f in %s", lastName, firstName, hoursWorked, name);
  }
}
