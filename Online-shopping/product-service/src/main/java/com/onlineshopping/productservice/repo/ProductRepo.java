package com.onlineshopping.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshopping.productservice.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
