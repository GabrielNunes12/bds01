package com.devsuperior.bds01.services;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
  @Autowired
  private DepartmentRepository departmentRepository;
  public List<DepartmentDTO> findAll() {
    return departmentRepository.findAll().stream()
            .sorted(Comparator.comparing(Department::getName))
            .map(DepartmentDTO::new)
            .collect(Collectors.toList());
  }
}
