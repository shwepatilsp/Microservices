package com.management.employeeapp.feignclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.management.employeeapp.response.AddressResponse;

@FeignClient(name="address-service" , path="/address-app/api/")
@RibbonClient(name="address-service")
public interface AddressClient { // you don't need to write any implementation for this class
	//spring will create implementation and will inject bean proxy
	
	@GetMapping("/address/{id}")
	ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id);

}
