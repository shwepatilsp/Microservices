package com.onlineshopping.productservice.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.productservice.dto.ProductRequest;
import com.onlineshopping.productservice.dto.ProductResponse;
import com.onlineshopping.productservice.model.Product;
import com.onlineshopping.productservice.repo.ProductRepo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	public void createProduct(ProductRequest productRequest) {
		
			Product product = modelMapper.map(productRequest, Product.class);
			productRepo.save(product);
			System.out.println("Product sis saved");

	}
	public List<ProductResponse> getAllProducts() {
		
		List<Product> products = productRepo.findAll();
		List<ProductResponse> productResponse = Arrays.asList(modelMapper.map(products, ProductResponse[].class));
		return productResponse;
		
	}

}
