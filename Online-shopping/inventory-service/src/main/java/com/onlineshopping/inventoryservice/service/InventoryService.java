package com.onlineshopping.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineshopping.inventoryservice.dto.InventoryResponse;
import com.onlineshopping.inventoryservice.repo.InventoryRepo;


@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepo inventoryRepo;
	
	@Transactional(readOnly=true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
	
		return inventoryRepo.findBySkuCodeIn(skuCode).stream()
			    .map(inventory -> {
			        InventoryResponse response = new InventoryResponse();
			        response.setSkuCode(inventory.getSkuCode());
			        response.setInStock(inventory.getQuantity() > 0);
			        return response;
			    })
			    .collect(Collectors.toList());
	}

}
