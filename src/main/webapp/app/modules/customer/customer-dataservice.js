'use strict';

customer.factory('OrderDataService',['$log','httpFactory',function($log,httpFactory){
	return new OrderDataService($log,httpFactory);
}]);


function OrderDataService($log,httpFactory){

	this.httpFactory = httpFactory;
		
	this.save = function(saveDetails){
		var saveOrder = './service/orders/save';
		return httpFactory.apiService(saveOrder,'post',saveDetails);
	}
	
	this.listOrders = function(){
		var orders = './service/orders/listOrders';
		return httpFactory.apiService(orders,'get');
	}
}