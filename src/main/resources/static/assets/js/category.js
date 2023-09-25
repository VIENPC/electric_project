var app = angular.module('my-app');

app.controller('category-controller', function ($scope, $http, $window) {
    $scope.categoris = [];
    $scope.brands = [];
    $scope.products = [];

    $http.get('/rest/category')
        .then(function (response) {
            $scope.categoris = response.data;
        });

    $http.get('/rest/brand')
        .then(function (response) {
            $scope.brands = response.data;
        });

    $http.get('/rest/product')
        .then(function (response) {
            $scope.products = response.data;
        });
        
    $scope.goToSinglePage = function (productID) {
        window.location.href = `/shop`; // Sử dụng chuỗi template (ES6)
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