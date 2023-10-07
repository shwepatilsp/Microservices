package com.onlineshopping.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import com.onlineshopping.notificationservice.event.OrderPlacedEvent;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	
	@KafkaListener(topics="notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
		//send out email notification
		
		System.out.print("Received  Notification for order "+orderPlacedEvent.getOrderNo());
	}
}
