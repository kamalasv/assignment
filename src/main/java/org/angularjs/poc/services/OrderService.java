package org.angularjs.poc.services;

import org.angularjs.poc.dto.OrderDto;

public interface OrderService {

	boolean saveOrder(OrderDto orderDto);
	
	OrderDto listOrders();
}
