package com.management.addressapp.service;

import java.util.Arrays;
import java.util.List;

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

	public List<AddressResponse> getAllAddress() {
		List<Address> addressList = addressRepo.findAll();
		List<AddressResponse> addressResponse = Arrays.asList(modelMapper.map(addressList, AddressResponse[].class));
		return addressResponse;
	}

}
