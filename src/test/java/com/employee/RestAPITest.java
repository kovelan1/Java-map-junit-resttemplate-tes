package com.employee;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.employee.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class RestAPITest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@Test
	@Order(1)
	public void testCreateEmployee() {
		 
		HttpHeaders headers = new HttpHeaders();
		Employee employee = new Employee("emp001", "john deo", "NY", "test@email.com");
		 
		HttpEntity<Employee>   request =new HttpEntity<>(employee,headers);
		
		ResponseEntity<Employee> result = restTemplate.postForEntity("http://localhost:"+port+"/employee", request, Employee.class);
		
		
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(employee, request.getBody());
		
		
	}
	
	@Test
	@Order(2)
	public void testGetAllEmployee() {
		testCreateEmployee();
		
		ResponseEntity<Employee[]> result =
				  restTemplate.getForEntity(
						  "http://localhost:"+port+"/employeeList",
				  Employee[].class);
		List<Employee> employees=Arrays.asList(result.getBody());
		
		assertEquals(200, result.getStatusCodeValue());
		
		assertThat(employees).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testGetEmployeeById() {
		
		testCreateEmployee();
		
		ResponseEntity<Employee> result =
				  restTemplate.getForEntity(
						  "http://localhost:"+port+"/employee/emp001",
				  Employee.class);
		
		assertEquals(200, result.getStatusCodeValue());
		assertEquals("emp001", result.getBody().getId());
	}
	
	@Test
	@Order(4)
	public void testUpdateEmployee() {
		testCreateEmployee();
		 
		HttpHeaders headers = new HttpHeaders();
		Employee employee = new Employee("emp001", "john deo", "WA", "test@email.com");
		 
		HttpEntity<Employee>   request =new HttpEntity<>(employee,headers);
		
		ResponseEntity<Employee> result = restTemplate.exchange("http://localhost:"+port+"/employee/emp001",HttpMethod.PUT, request, Employee.class);
		
		
		assertEquals(200, result.getStatusCodeValue());
		assertNotEquals("NY", request.getBody().getLocation());
		
		
	}
	
	@Test
	@Order(5)
	public void testDeleteEmployee() {
		testCreateEmployee();
		 
		HttpHeaders headers = new HttpHeaders();
		Employee employee = new Employee("emp001", "john deo", "WA", "test@email.com");
		 
		HttpEntity<Employee>   request =new HttpEntity<>(employee,headers);
		
		ResponseEntity<Employee> result = restTemplate.exchange("http://localhost:"+port+"/employee/emp001",HttpMethod.DELETE, request, Employee.class);
		
		
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(employee, request.getBody());
		
	}
	

}
