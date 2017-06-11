package org.angularjs.poc.services;

import java.math.BigInteger;

import org.angularjs.poc.dao.OrderDao;
import org.angularjs.poc.dto.OrderDto;
import org.angularjs.poc.pojo.Customer;
import org.angularjs.poc.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public boolean saveOrder(OrderDto order) {
		boolean response = false;
		
		Customer customer = new Customer(BigInteger.valueOf(Double.valueOf(order.getCustomerId()).longValue()));
		
		Order orderObj = new Order();
		// customer object
		orderObj.setCustomer(customer);
		// order item name
		orderObj.setOrderItem(order.getOrderItem());
		// order item description
		orderObj.setItemDesc(order.getItemDesc());
		// order date
		orderObj.setOrderDate(order.getOrderDate());
		// save the value
		Order resp = orderDao.saveOrder(orderObj);
		
		if(resp.getOrderId().intValue() > 0){
			response = true;
		}
		return response;
	}

	@Override
	public OrderDto listOrders() {
		return orderDao.listOrders();
	}

}
