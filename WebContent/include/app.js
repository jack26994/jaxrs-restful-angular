(() => {
	var app = angular.module('app', ['ngSanitize', 'ngRoute']);
	
	app.controller('HttpCtrl', ($scope, $http) => {
		$scope.customHome = '';
		$scope.navTitle = 'All Users';
		$scope.operation = '';
		$scope.userLogged = false;
		
		var response = $http.get('/RestfulWS/rest/actors/');
		response.success((data) => {
			$scope.actors = data;
			console.log("[main] # of items: " + data.length)
			angular.forEach(data, element => {
				console.log("[main] actor: " + element.name);
			});
		});	
		
		response.error((data, status, headers, config) => {
			alert("AJAX failed to get data, status=" + status);
		})
		
		$scope.clearForm = () => {
			$scope.actor = {
				id:'',
				name:'',
				email:'',
				password:'',				
			};
		}
		
		$scope.login = (email, password) => {
			$scope.jsonObj = angular.toJson($scope.actor, false);
			console.log("[update] data: " + $scope.jsonObj);
			
			if (email && password) {
				var response = $http.post('RestfulWS/rest/actors/login', $scope.jsonObj);
				response.success((data, status) => {
					newUser = data;
					$scope.resetSearch();
					
					console.log(newUser.name);
					
					if (newUser.name) {
						console.log('Session created: ' + angular.toJson(data, false) + ", status = " + status);
						$scope.userLogged = true;
						$scope.actor = newUser;
					} else {
						console.log('User does not exist');
					}
				});			
				
				response.error((data, status) => {
					alert('AJAX failed, status = ' + status);
				});
			}
		};
		
		$scope.resetSearch = name => {
			
			$scope.customHome = "";
			$scope.operation="";
			$scope.clearForm();
			$scope.userLogged = false;
			$scope.navTitle = 'All Users';
			$scope.searchName = '';
			
			var response = $http.get('/RestfulWS/rest/actors/');
			response.success(data => {
				$scope.actors = data;
				
				console.log("[resetSearch] # of items: " + data.length)
		    });
			
			response.error((data, status, headers, config) => {
				alert("AJAX failed to get data, status=" + status);
			});
		};
	});
})();