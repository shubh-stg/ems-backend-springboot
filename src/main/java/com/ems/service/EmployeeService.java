package com.ems.service;

import java.util.List;

import com.ems.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto createemployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeById(Long id);
	
	List<EmployeeDto>getAllEmployee();
	
	EmployeeDto updateEmployee(Long id ,EmployeeDto updatedEmployee);
	
	void deleteEmployee(Long id);
}
