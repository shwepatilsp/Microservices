package com.management.addressapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.addressapp.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    
    @Query(nativeQuery = true, value = "SELECT ea.id, ea.lane1, ea.lane2, ea.state, ea.zip,ea.employee_id FROM Management.address ea join Management.employee e on e.id = ea.employee_id where ea.employee_id = :employeeId")
    Address findAddressByEmployeeId(int employeeId);
}

