package com.employee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.employee.model.Employee;

@Service
public class EmployeeService {
	
	
	
	private Map<String,Employee> employeeStore= new HashMap<>();
	
	public Employee  createEmployee(Employee employee) {
		return employeeStore.put(employee.getId(), employee);
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> employees=new ArrayList<Employee>();
		
		employeeStore.forEach((key,employee) -> {
			employees.add(employee);
		});
		
		return employees;
	}
	
	public Employee getEmployeebyId(String employeeId) {
		return employeeStore.get(employeeId);
	}
	
	public Employee updateEmployee(String employeeId, Employee employee) {
		employee.setId(employeeId);
		return employeeStore.replace(employeeId, employee);
//		empoloyeeStore.put(employeeId,employee);
	}
	
	public Employee deleteEmployee(String employeeId) {
		return employeeStore.remove(employeeId);
	}
	
	

}
