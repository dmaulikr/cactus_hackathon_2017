var app = angular.module('pli', [
    'ngRoute',          //$routeProvider
]);

app.controller('sooqa-controller', ['$scope',
function($scope) {
    $scope.sooqa = 'SOOOOQA';
}])