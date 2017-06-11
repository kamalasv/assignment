package org.angularjs.poc.dao;

import org.angularjs.poc.dto.OrderDto;
import org.angularjs.poc.pojo.Order;

public interface OrderDao{

	Order saveOrder(Order order);
	
	OrderDto listOrders();
}
