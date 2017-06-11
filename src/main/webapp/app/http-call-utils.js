customer.factory('httpFactory', ['$http', function($http){
	
	return {
		apiService:function(url,method,data,extra) {
			return $http({
				method:method,
				url:url,
				headers:$http.defaults.headers.common,		
				data:data
			});
		}
	}
}])
