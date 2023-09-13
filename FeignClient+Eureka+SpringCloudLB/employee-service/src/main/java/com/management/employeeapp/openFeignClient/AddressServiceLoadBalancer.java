package com.management.employeeapp.openFeignClient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

import feign.Feign;

@LoadBalancerClient(value="address-service",configuration = MyCustomLoadBalancerConfiguration.class)
public class AddressServiceLoadBalancer {
	
	@LoadBalanced
	public Feign.Builder feignBuilder(){
		return Feign.builder();
	}

}
