package org.angularjs.poc.dto;

import java.util.Date;
import java.util.List;

public class OrderDto {

	private String customerId;
	private String orderItemId;
	private String orderItem;
	private String itemDesc;
	private Date orderDate;
	private String message;
	private List<OrderDto> orders;
	
	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getOrderItem() {
		return orderItem;
	}
	
	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}
	
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
}
