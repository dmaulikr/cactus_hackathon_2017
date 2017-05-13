var app = angular.module('pli', [
    'ngRoute',          //$routeProvider
]);
app.constant('baseUrl', 'http://178.62.238.65:8841/');
app.config(['$routeProvider', '$httpProvider', '$locationProvider',
    function ($routeProvider, $httpProvider, $locationProvider) {
        $routeProvider.when('/', {
            templateUrl: '../demos.html'
        }).when('/login', {
            templateUrl: '../login.html'
        }).when('/demos', {
            templateUrl: '../demos.html'
        });
        $httpProvider.interceptors.push('authInterceptor');
        $locationProvider.hashPrefix('');
    }
]);
app.factory('$authProvider', ['$q',"$window", function ($q,$window) {
    return {
        isLoggedIn: function () {
            return (localStorage.access_token != 'undefined' && Boolean(localStorage.access_token));
        }
    };
}]);
app.run(['$rootScope', '$location', '$authProvider', function ($rootScope, $location, $authProvider) {
    $rootScope.$on('$routeChangeStart', function () {
        if (!$authProvider.isLoggedIn()) {
            $location.path('/login').replace();
        }
    });
}]);
app.factory('authInterceptor', ["$q", "$window", "$location", '$rootScope',
function ($q, $window, $location, $rootScope) {
    return {
        request: function (config) {
            if (localStorage.access_token && config.method) {
                //HttpBearerAuth
                config.headers.Authorization = localStorage.access_token;
                $rootScope.authToken = localStorage.access_token;
                //console.log("Token: " + $rootScope.authToken);
            }
            return config;
        },
        responseError: function (rejection) {
            if (rejection.data.code === "UNAUTHORIZED") {
                $location.path('/logout').replace();
            }
            return $q.reject(rejection);
        }
    };
}]);