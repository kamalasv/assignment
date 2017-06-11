package org.angularjs.poc.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.angularjs.poc.dao.OrderDao;
import org.angularjs.poc.dto.OrderDto;
import org.angularjs.poc.pojo.Order;
import org.angularjs.poc.services.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.TestCase;

public class OrderControllerTest extends TestCase {

	private OrderServiceImpl orderService;
	private OrderDao orderDao;
	
	@Before
	public void setUp() throws Exception {
		orderService = new OrderServiceImpl();
		orderDao = Mockito.mock(OrderDao.class);
		orderService.setOrderDao(orderDao);
	}


	@Test
	public void testOrderSave() {
		
		Order odr = new Order();
		odr.setOrderItem("Test Item");
		odr.setItemDesc("Test Item Desc");
		odr.setOrderDate(new Date());
		
		Order odrResp = new Order();
		odrResp.setOrderId(new BigInteger("1"));
		
		Mockito.when(orderDao.saveOrder(odr)).thenReturn(odrResp);
		
		OrderDto order = new OrderDto();
		order.setCustomerId("1");
		order.setOrderItem("Test Item");
		order.setItemDesc("Test Item Desc");		
		order.setOrderDate(new Date());
		
		boolean resp = orderService.saveOrder(order);
		assertEquals(true, resp);
	}

	@Test
	public void testListAllOrders(){
		
		List<OrderDto> ordersList = new ArrayList<OrderDto>();
		OrderDto order1 = new OrderDto();
		order1.setCustomerId("1");
		order1.setOrderItemId("1");
		order1.setOrderItem("Item One");
		order1.setItemDesc("Item One Desc");
		ordersList.add(order1);
		
		OrderDto order2 = new OrderDto();
		order2.setCustomerId("1");
		order2.setOrderItemId("2");
		order2.setOrderItem("Item Two");
		order2.setItemDesc("Item Two Desc");
		ordersList.add(order2);
		
		OrderDto odrList = new OrderDto();
		odrList.setOrders(ordersList);
		
		
		Mockito.when(orderDao.listOrders()).thenReturn(odrList);
		
		OrderDto orders = orderService.listOrders();
		assertNotNull(orders);
	}
}
