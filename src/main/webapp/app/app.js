'use strict';

var customer = angular.module( 'customer', ['ui.router','ngResource'] );

customer.run(function($log,$state){

	$log.info("Application Started");
	
	$state.go('login');
		
});