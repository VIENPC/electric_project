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


                $http.get('/rest/comment/' + productID)
                    .then(function (response) {
                        alert("vao day");
                        console.log('Giá trị comment:', response); // Add this line


                    })
                    .catch(function (error) {
                        console.error('Error fetching item details:', error);
                    });
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
    $scope.comment = [];

    $scope.goToProduct_Detail = function (productID) {
        window.location.href = `/detail/${productID}`;
    };




});