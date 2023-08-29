package com.management.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.management.employeeapp.entity.Employee;
import com.management.employeeapp.repo.EmployeeRepo;
import com.management.employeeapp.response.AddressResponse;
import com.management.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo EmployeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	private RestTemplate restTemplate;
	
//	@Value("${addresservice.base.url}")
//	private String addressBaseURL;
	
	public EmployeeService(@Value("${addresservice.base.url}") String addressBaseURL,RestTemplateBuilder builder){
		this.restTemplate=builder.rootUri(addressBaseURL)
				.build();
	}

	public EmployeeResponse getEmployeeById(int id) {
		
	Employee employee= EmployeeRepo.findById(id).get();
	
	EmployeeResponse employeeResponse =modelMapper.map(employee,EmployeeResponse.class);
	
//	EmployeeResponse employeeResponse = new EmployeeResponse();
//	employeeResponse.setId(id);
//	employeeResponse.setName(employee.getName());
//	employeeResponse.setEmail(employee.getEmail());	
//	employeeResponse.setBloodGroup(employee.getBloodGroup());
	
	
	AddressResponse addressResponse = restTemplate.getForObject("/address/{id}",AddressResponse.class ,id);
	
	employeeResponse.setAddressResponse(addressResponse);
	
	return employeeResponse;
		
	}
}
