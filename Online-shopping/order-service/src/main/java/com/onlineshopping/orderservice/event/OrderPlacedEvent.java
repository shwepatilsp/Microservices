package com.onlineshopping.orderservice.event;

public class OrderPlacedEvent {
	private String orderNo;
	
	public OrderPlacedEvent(String orderNo) {
		this.orderNo=orderNo;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
