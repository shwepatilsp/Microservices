package com.management.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
	
	@Autowired
	private WebClient webClient;
	
	//@Autowired
	//private RestTemplate restTemplate;
	
//	@Value("${addresservice.base.url}")
//	private String addressBaseURL;
	
//	public EmployeeService(@Value("${addresservice.base.url}") String addressBaseURL,RestTemplateBuilder builder){
//		this.restTemplate=builder.rootUri(addressBaseURL)
//				.build();
//	}

	public EmployeeResponse getEmployeeById(int id) {
		
	Employee employee= EmployeeRepo.findById(id).get();
	
	EmployeeResponse employeeResponse =modelMapper.map(employee,EmployeeResponse.class);
	
//	EmployeeResponse employeeResponse = new EmployeeResponse();
//	employeeResponse.setId(id);
//	employeeResponse.setName(employee.getName());
//	employeeResponse.setEmail(employee.getEmail());	
//	employeeResponse.setBloodGroup(employee.getBloodGroup());
	
	
	//AddressResponse addressResponse = restTemplate.getForObject("/address/{id}",AddressResponse.class ,id);
	
	//For Blocking call
//	AddressResponse addressResponse = webClient
//										.get()
//										.uri("/address/"+id)
//										.retrieve()
//										.bodyToMono(AddressResponse.class)
//										.block();
//			
//	employeeResponse.setAddressResponse(addressResponse);
	
	//For non blocking call
	//AddressResponse addressResponse = new AddressResponse();
	
	webClient.get()
    .uri("/address/" + id)
    .retrieve()
    .bodyToMono(AddressResponse.class)
    .subscribe(
        response -> {
            // Set the addressResponse when the response is received
            employeeResponse.setAddressResponse(response);

            // Any code that depends on addressResponse should be here
            // This code executes asynchronously after the response is received
        },
        throwable -> {
            // Handle errors
            throwable.printStackTrace();
        }
    );

	
	return employeeResponse;
		
	}
}
