package com.employee.model;

public class Employee {
	
	private String id;
	private String name;
	private String location;
	private String email;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Employee(String id, String name, String location, String email) {
	
		this.id = id;
		this.name = name;
		this.location = location;
		this.email = email;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
