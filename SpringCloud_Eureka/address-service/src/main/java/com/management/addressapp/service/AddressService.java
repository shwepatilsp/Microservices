package com.management.addressapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.addressapp.response.AddressResponse;
import com.management.addressapp.entity.Address;
import com.management.addressapp.repo.AddressRepo;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressResponse findAddressByEmployeeId(int employeeId) {
		
		Address address=addressRepo.findAddressByEmployeeId(employeeId);
		
		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
		
		return addressResponse;
	}

}
