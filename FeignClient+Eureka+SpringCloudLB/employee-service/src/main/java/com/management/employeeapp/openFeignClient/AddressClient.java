package com.management.employeeapp.openFeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.management.employeeapp.response.AddressResponse;


@FeignClient(name="address-service", path="/address-app/api/")
public interface AddressClient {
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id);
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressResponse>> getAllAddress();

}
