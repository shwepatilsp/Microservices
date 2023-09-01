package com.management.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.management.employeeapp.entity.Employee;
import com.management.employeeapp.feignclient.AddressClient;
import com.management.employeeapp.repo.EmployeeRepo;
import com.management.employeeapp.response.AddressResponse;
import com.management.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo EmployeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressClient addressClient;
	
	public EmployeeResponse getEmployeeById(int id) {
		
	Employee employee= EmployeeRepo.findById(id).get();
	
	EmployeeResponse employeeResponse =modelMapper.map(employee,EmployeeResponse.class);
	
//	EmployeeResponse employeeResponse = new EmployeeResponse();
//	employeeResponse.setId(id);
//	employeeResponse.setName(employee.getName());
//	employeeResponse.setEmail(employee.getEmail());	
//	employeeResponse.setBloodGroup(employee.getBloodGroup());
	
	
	ResponseEntity<AddressResponse> addressEntity = addressClient.getAddressByEmployeeId(id);
	AddressResponse addressResponse = addressEntity.getBody();
    
	employeeResponse.setAddressResponse(addressResponse);

	
	return employeeResponse;
		
	}
}
