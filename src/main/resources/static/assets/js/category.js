var app = angular.module('my-app');

app.controller('category-controller', function ($scope, $http, $window) {
    $scope.categoris = [];
    $scope.brands = [];
    $scope.products = [];
    $scope.newCate = {};
    $scope.newBrand = {};
    $scope.form = {};

    $http.get('/rest/category')
        .then(function (response) {
            $scope.categoris = response.data;
            console.log($scope.categoris);

        });

    $http.get('/rest/brand')
        .then(function (response) {
            $scope.brands = response.data;
        });


    $http.get('/rest/product')
        .then(function (response) {
            $scope.products = response.data;
        });

    $scope.goToCategory = function (categoryID) {
        window.location.href = `/shop#${categoryID}`;
    };

    $scope.goToBrand = function (brandID) {
        window.location.href = `/shop#${brandID}`;
    };

    // Hàm tìm kiếm sản phẩm theo tên
    $scope.searchProductsByName = function () {
        $http.get('/rest/product-search?productName=' + $scope.searchText)
            .then(function (response) {
                $scope.products = response.data;
                if ($scope.products.length === 0) {
                    swal("Thất bại", "Không tìm thấy sản phẩm!", "error")
                }
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };

    // Hàm xử lý sự kiện chuyển về Tab 1
    $scope.switchToTab1 = function () {
        $('#myTabs a[href="#tab1"]').tab('show');
    };

    $scope.editbrand = function (brands) {
        $scope.newBrand = angular.copy(brands);
        $scope.switchToTab1();
    }


});

