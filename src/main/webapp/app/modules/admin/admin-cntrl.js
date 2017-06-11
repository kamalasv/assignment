'use strict';

customer.controller('AdminCntrl', ['$log','OrderDataService', function($log,OrderDataService){

	$log.info('AdminCntrl invoked');

	var vm = this;
	
	OrderDataService.listOrders().then(function(data){
		$log.info("saveDetails reason data ----> success" +JSON.stringify(data));
		vm.orders = data.data.orders;
		$log.info("vm.orders" +JSON.stringify(vm.orders));
	},function(reason){
		$log.info("saveDetails reason data ----> failed");
	});
}]);