'use strict';

customer.controller('LoginCntrl', ['$log','$state', function($log,$state){

	$log.info('LoginCntrl invoked');

	var vm = this;
	
	vm.validateUser = this.validateUser;

	vm.userTypes = ["Admin","Customer"];
	
	this.validateUser = function(){
		$log.info('validate function called'+vm.selectedType);
		
		if(vm.selectedType === 'Admin'){
			$state.go('admin');
		}else if(vm.selectedType === 'Customer'){
			$state.go('customer');
		}
		
	}
}]);