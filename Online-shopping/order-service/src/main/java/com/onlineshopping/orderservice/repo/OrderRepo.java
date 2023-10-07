package com.onlineshopping.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineshopping.orderservice.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
