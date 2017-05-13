app.controller('loginController', ['$rootScope', '$scope', '$http', 'baseUrl', '$location',
function($rootScope, $scope, $http, baseUrl, $location) {
    $scope.email;
    $scope.password;
    $scope.login = function() {
        $http.post(baseUrl + 'login/', {email: $scope.email, password: $scope.password})
        .then(function(response, status, headers) {
            console.log(response);
            var accessToken = response.data.token;
            $rootScope.name = response.data.name;
            if (accessToken) {
                $rootScope.authToken = accessToken;
                localStorage.access_token = accessToken;
                console.log(localStorage);
                $location.path('/').replace();
            }
        }, function() {});
    }
    $scope.register = function() {

    }
}]);