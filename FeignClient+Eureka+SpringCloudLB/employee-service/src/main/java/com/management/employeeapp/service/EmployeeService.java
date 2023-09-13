package com.management.employeeapp.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.management.employeeapp.entity.Employee;
import com.management.employeeapp.openFeignClient.AddressClient;
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
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AddressClient addressClient;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
//	@Autowired
//    private LoadBalancerClient loadBalancerClient;

	public EmployeeResponse getEmployeeById(int id) {
		
	Employee employee= EmployeeRepo.findById(id).get();
	
	EmployeeResponse employeeResponse =modelMapper.map(employee,EmployeeResponse.class);
	
	ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(id);
	AddressResponse addressResponse=addressResponseEntity.getBody();
	
	employeeResponse.setAddressResponse(addressResponse);
	
	return employeeResponse;
		
	}
	
	public List<EmployeeResponse> getAllEmployees() {
		List<Employee> employeeList = EmployeeRepo.findAll();
		
	    List<EmployeeResponse> employeesResponse = Arrays.asList(modelMapper.map(employeeList, EmployeeResponse[].class));
		
	    ResponseEntity<List<AddressResponse>> allAddress = addressClient.getAllAddress();
		
	    List<AddressResponse> addressesResponse = allAddress.getBody();
	    
	    employeesResponse.forEach(employee->{
	    	for(AddressResponse addressResponse:addressesResponse) {
	    		if(addressResponse.getEmployee_id()==employee.getId()) {
	    			employee.setAddressResponse(addressResponse);
	    		}
	    	}
	    }
	    );
	    
	    return employeesResponse;
	}
	
	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
//		List<ServiceInstance> instances = discoveryClient.getInstances("address-service");
//		
//		ServiceInstance serviceInstance=instances.get(0);
//		
//		String uri= serviceInstance.getUri().toString();
//		
//		System.out.println("uri :::::: "+uri);
		
//		ServiceInstance serviceInstance=loadBalancerClient.choose("address-service");
//		
//		String uri= serviceInstance.getUri().toString();
//		
//		String contextPath=serviceInstance.getMetadata().get("configPath");
//		
//		System.out.println("uri :::::: "+uri);
		
		
		return restTemplate.getForObject("http://address-service/address-app/api/address/{id}", AddressResponse.class ,id);
	}
	
	private AddressResponse callingAddressServiceUsingWebClient(int id) {
		return webClient.get()
			    .uri("/address/" + id)
			    .retrieve()
			    .bodyToMono(AddressResponse.class)
			    .block();
	}

	
}
