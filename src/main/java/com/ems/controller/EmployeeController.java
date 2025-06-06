package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.EmployeeDto;
import com.ems.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<EmployeeDto>createEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto createemployee = employeeService.createemployee(employeeDto);
		return new ResponseEntity<>(createemployee,HttpStatus.CREATED);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto>getEmployeeById(@PathVariable("id") Long id){
		EmployeeDto employeeById = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employeeById,HttpStatus.OK);
		
	}
	@GetMapping
	public ResponseEntity<List<EmployeeDto>>getAllEmployees(){
		List<EmployeeDto> allEmployee = employeeService.getAllEmployee();
		return new ResponseEntity<>(allEmployee,HttpStatus.OK);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto>updateEmp(@PathVariable("id") Long id , @RequestBody EmployeeDto employeeDto){
		EmployeeDto updateEmployee = employeeService.updateEmployee(id, employeeDto);
		return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteEmployee(@PathVariable("id") Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok("Employee Deleted Successfully");
	}
}
