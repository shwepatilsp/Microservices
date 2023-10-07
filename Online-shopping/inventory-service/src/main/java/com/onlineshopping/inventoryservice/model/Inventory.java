package com.onlineshopping.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_inventory")
public class Inventory {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String skuCode;
private Integer quantity;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getSkuCode() {
	return skuCode;
}
public void setSkuCode(String skuCode) {
	this.skuCode = skuCode;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}


}
