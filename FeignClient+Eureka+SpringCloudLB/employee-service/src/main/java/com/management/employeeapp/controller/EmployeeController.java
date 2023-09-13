package com.management.employeeapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.management.employeeapp.response.EmployeeResponse;
import com.management.employeeapp.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService EmployeeService;
	
	@GetMapping("/employees")
	ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
		
		List<EmployeeResponse> employeeResponse= EmployeeService.getAllEmployees();
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
		
	}
	
	@GetMapping("/employees/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
		
		EmployeeResponse employeeResponse= EmployeeService.getEmployeeById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
		
	}

}
