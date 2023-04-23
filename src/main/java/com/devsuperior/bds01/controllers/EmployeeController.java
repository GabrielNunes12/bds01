package com.devsuperior.bds01.controllers;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
  @Autowired
  private EmployeeService service;

  @GetMapping
  public ResponseEntity<Page<EmployeeDTO>> findAll(Pageable pageable) {
    Page<EmployeeDTO> employees = service.findAll(pageable);
    return ResponseEntity.ok().body(employees);
  }

  @PostMapping
  public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO employeeDTO) {
    employeeDTO = service.insert(employeeDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(employeeDTO.getId()).toUri();
    return ResponseEntity.created(uri).body(employeeDTO);
  }
}
