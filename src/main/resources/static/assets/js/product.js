var app = angular.module('my-app');

app.controller('product-controller', function ($scope, $http, $window) {
    $scope.products = [];
    $scope.brands = [];
    $scope.products = {};


    $http.get('/rest/product')
        .then(function (response) {
            $scope.products = response.data;
        });
    $http.get('/rest/brand')
        .then(function (response) {
            $scope.brands = response.data;
        });
    $scope.goToSinglePage = function (productID) {
        window.location.href = `/detail/${productID}`; // Sử dụng chuỗi template (ES6)
    };

    $scope.loadProductsByBrand = function (brandID) {
        $http.get('/rest/products-by-brand?brandID=' + brandID)
            .then(function (response) {
                $scope.products = response.data;
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };

    $scope.loadProductsByCategory = function (categoryID) {
        $http.get('/rest/products-by-category?categoryID=' + categoryID)
            .then(function (response) {
                $scope.products = response.data;
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };


});