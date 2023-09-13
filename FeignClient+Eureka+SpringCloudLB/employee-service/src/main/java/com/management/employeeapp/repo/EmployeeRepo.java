package com.management.employeeapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.employeeapp.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>{

}
