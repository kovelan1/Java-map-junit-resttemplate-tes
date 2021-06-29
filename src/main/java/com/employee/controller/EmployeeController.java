package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public Employee createRecord(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/employeeList")
	public List<Employee> getAllEmployeesList(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable(value = "id") String employeeId) {
		return employeeService.getEmployeebyId(employeeId);
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployeeData(@PathVariable(value = "id") String employeeId,@RequestBody Employee employee ) {
		 return employeeService.updateEmployee(employeeId, employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public Employee deleteEmployee(@PathVariable(value = "id") String employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
	
	//health check 
	//   url//actuator/health
}
