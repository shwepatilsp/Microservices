package com.onlineshopping.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.onlineshopping.orderservice.dto.InventoryResponse;
import com.onlineshopping.orderservice.dto.OrderLineItemsDto;
import com.onlineshopping.orderservice.dto.OrderRequest;
import com.onlineshopping.orderservice.event.OrderPlacedEvent;
import com.onlineshopping.orderservice.model.Order;
import com.onlineshopping.orderservice.model.OrderLineItems;
import com.onlineshopping.orderservice.repo.OrderRepo;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

	public String placeOrder(OrderRequest orderRequest) {

		Order order = new Order();

		order.setOrderNo(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream()
				.map(orderLineItemsDto -> maptoDTO(orderLineItemsDto)).toList();

		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes = order.getOrderLineItemsList().stream()
		.map(orderLineIteam -> orderLineIteam.getSkuCode()).toList();

		// call to inventory service and place order if product is in stock
		

		InventoryResponse[] InventoryResponseArray = webClientBuilder.build().get()
	            .uri("http://inventory-service/api/inventory"
	    		, uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve().bodyToMono(InventoryResponse[].class)
				.block();
		
		//It will check isInStock in true for all products , if true then all products in stock
		boolean allProductsInStock = Arrays.stream(InventoryResponseArray)
				.allMatch(InventoryResponse::isInStock); 
		
		
		if (allProductsInStock) {
			orderRepo.save(order);
			kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNo()));
			return "Order Placed Successfully";
		} else {
			throw new IllegalArgumentException("Product not in stock, Please try again later");
		}
	}

	private OrderLineItems maptoDTO(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}


}
