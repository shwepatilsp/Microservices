package com.onlineshopping.inventoryservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshopping.inventoryservice.model.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long>{
	List<Inventory> findBySkuCodeIn(List<String> skuCode);


}
