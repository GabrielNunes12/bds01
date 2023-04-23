package com.devsuperior.bds01.dto;

import com.devsuperior.bds01.entities.Department;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DepartmentDTO implements Serializable {
  private String name;

  public DepartmentDTO(String name) {
    this.name = name;
  }

  public DepartmentDTO() {
  }

  public DepartmentDTO(Department department) {
    this.name = department.getName();
  }
}
