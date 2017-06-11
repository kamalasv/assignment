'use strict';

customer.config(function($urlRouterProvider,$logProvider,$stateProvider) {

	$urlRouterProvider.otherwise('/login');

	$stateProvider
	.state('login', {
		url: '/login',
		templateUrl: 'app/modules/login/login.html',
		controller: 'LoginCntrl',
		controllerAs: 'vm'
	})
	.state('admin', {
		url: '/adminDashboard',
		templateUrl: 'app/modules/admin/dashboard.html',
		controller: 'AdminCntrl',
		controllerAs: 'vm'
	})
	.state('customer', {
		url: '/customerDashboard',
		templateUrl: 'app/modules/customer/dashboard.html',
		controller: 'CustomerCntrl',
		controllerAs: 'vm'
	});

	$logProvider.debugEnabled(false);
});