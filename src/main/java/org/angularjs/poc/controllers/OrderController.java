package org.angularjs.poc.controllers;

import org.angularjs.poc.dto.OrderDto;
import org.angularjs.poc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/save", method = { RequestMethod.POST, RequestMethod.OPTIONS })
	@ResponseBody
	public  OrderDto saveCompanyDetails(@RequestBody final OrderDto orderDto){
		System.out.println("order item ---->"+orderDto.getOrderItem());
		boolean resp = orderService.saveOrder(orderDto);
		OrderDto orderResp = new OrderDto();
		if(resp){
			orderResp.setMessage("Order Details Saved Successfully!");
		}else{
			orderResp.setMessage("Order Details Not Saved!");
		}
		return orderResp;
    }
	
	@RequestMapping(value="/listOrders", method = { RequestMethod.GET, RequestMethod.OPTIONS })
	@ResponseBody
	public  OrderDto listOrders(){
		OrderDto response = orderService.listOrders();
		return response;
	}
}
