'use strict';

customer.controller('CustomerCntrl', ['$log','$filter','OrderDataService', function($log,$filter,OrderDataService){

	$log.info('CustomerCntrl invoked');

	var vm = this;
	
	vm.custId = "1";
	
	vm.orderDate = new Date();
	
	vm.saveItem = this.saveItem;
	
	vm.message = null;
	
	this.saveItem = function(){
		$log.info('save item method invoked');
		
		var saveDetails = {
			'customerId' : vm.custId,
			'orderItem' : vm.orderItem,
			'itemDesc' : vm.itemDesc,
			'orderDate' : $filter('date')(vm.orderDate,'yyyy-MM-dd')
		};
		
		$log.info('save item details ---->'+JSON.stringify(saveDetails));
		
		OrderDataService.save(saveDetails).then(function(data){
			$log.info("saveDetails reason data ----> success" +JSON.stringify(data));
			$log.info("saveDetails reason data ----> success" +data.data.message);
			vm.message = data.data.message;
			
			vm.orderItem = '';
			vm.itemDesc = '';
			vm.orderDate = new Date();
		},function(reason){
			$log.info("saveDetails reason data ----> failed");
		});
		
	}
}]);