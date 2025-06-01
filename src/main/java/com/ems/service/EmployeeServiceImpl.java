package com.ems.service;



import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.mapper.EmployeeMapper;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository; 
	
	@Override
	public EmployeeDto createemployee(EmployeeDto employeeDto) {
		Employee employee=EmployeeMapper.maptoempolyee(employeeDto);
		Employee save = employeeRepository.save(employee);
		return EmployeeMapper.maptoemployeeDto(save);
		
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		Employee employee= employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee Not Found With This ID"));
		return EmployeeMapper.maptoemployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> all = employeeRepository.findAll();
		return all.stream().map((emp)->EmployeeMapper.maptoemployeeDto(emp)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee) {
		Employee emp= employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee With Id "+ id + "Not Found"));
		
		
		emp.setFirstName(updatedEmployee.getFirstName());
		emp.setLastName(updatedEmployee.getLastName());
		emp.setEmail(updatedEmployee.getEmail());
		Employee save = employeeRepository.save(emp);
		return EmployeeMapper.maptoemployeeDto(save);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		
	}

}
