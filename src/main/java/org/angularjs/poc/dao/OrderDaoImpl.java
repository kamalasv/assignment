package org.angularjs.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.angularjs.poc.dto.OrderDto;
import org.angularjs.poc.pojo.Order;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractSpringDao implements OrderDao{


	@Override
	public Order saveOrder(Order order) {
		super.saveOrUpdate(order);
		return order;
	}

	@Override
	public OrderDto listOrders() {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query hibernateQuery = session.getNamedQuery("listAllOrders");
		List<Order> orders = (List<Order>) hibernateQuery.list();
		OrderDto resp = getAllOrders(orders);
		return resp;
	}

	private OrderDto getAllOrders(List<Order> orders){
		List<OrderDto> orderDto = new ArrayList<OrderDto>();
		for(Order o : orders){
			OrderDto oDto = new OrderDto();
			oDto.setCustomerId(o.getCustomer().getCustId().toString());
			oDto.setOrderItemId(String.valueOf(o.getOrderId().intValue()));
			oDto.setOrderItem(o.getOrderItem());
			oDto.setItemDesc(o.getItemDesc());
			oDto.setOrderDate(o.getOrderDate());
			orderDto.add(oDto);
		}
		OrderDto resp = new OrderDto();
		resp.setOrders(orderDto);
		return resp;
	}
}
