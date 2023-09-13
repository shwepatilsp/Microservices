package com.management.employeeapp.response;


public class EmployeeResponse {

	private int id;

	private String name;

	private String bloodGroup;

	private String email;
	
	private AddressResponse addressResponse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse=addressResponse;
	}
	
	public AddressResponse getAddressResponse() {
		return addressResponse;
	}
	
	
}
