package com.devsuperior.bds01.services;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.DepartmentRepository;
import com.devsuperior.bds01.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private DepartmentRepository departmentRepository;
  public EmployeeDTO insert(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    copyDtoToEntity(employeeDTO, employee);
    employee = employeeRepository.save(employee);
    return new EmployeeDTO(employee);
  }
  private void copyDtoToEntity(EmployeeDTO dto, Employee entity) {
    entity.setName(dto.getName());
    entity.setEmail(dto.getEmail());
    Department department = departmentRepository.getOne(dto.getDepartmentId());
    entity.setDepartment(department);
  }

  public Page<EmployeeDTO> findAll(Pageable pageable) {
    Page<Employee> page = employeeRepository.findAll(pageable);
    List<EmployeeDTO> list = page.stream().map(EmployeeDTO::new).sorted(Comparator.comparing(EmployeeDTO::getName)).collect(Collectors.toList());
    return new PageImpl<>(list, pageable, page.getTotalElements());
  }
}
