var app = angular.module('my-app');

app.controller('product_detail-controller', function ($scope, $http, $window) {
    $scope.products = [];
    $scope.product = {};
    $scope.relatedProducts = [];

    $scope.loadItemDetails = function (productID) {
        $http.get('/rest/detail/' + productID)
            .then(function (response) {
                console.log('Response:', response); // Add this line
                $scope.product = response.data.product;
                $scope.relatedProducts = response.data.relatedProducts;
            })
            .catch(function (error) {
                console.error('Error fetching item details:', error);
            });
    };
    window.onload = function () {
        // Lấy đoạn cuối của URL (được kỳ vọng là itemId)
        var productID = window.location.pathname.split('/').pop();
        console.log('productID:', productID);

        if (productID) {
            $scope.loadItemDetails(productID);
        }
    };
});