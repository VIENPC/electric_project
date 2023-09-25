var app = angular.module('my-app');

app.controller('product-controller', function ($scope, $http, $window) {
    $scope.products = [];
    $scope.brands = [];
    $scope.products2 = {};


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

    $scope.addToCart = function (masp) {
         alert(masp)
        var cart = this.products.find(cart => cart.masp == masp);
        if (cart) {
            cart.qty++;
            $scope.saveToLocalStorage();

        } else {
            var url = `/rest/product/${masp}`;
            $http.get(url).then(resp => {
                resp.data.qty = 1;
                $scope.products.push(resp.data);
                $scope.saveToLocalStorage();

            })
        }

        // swal("Thành công", "Thêm sản phẩm vào giỏ hàng thành công!", "success")


    }
    $scope.saveToLocalStorage = function () {
        var json = JSON.stringify(angular.copy($scope.products));
        localStorage.setItem("cart", json);
    }
    $scope.loadLocalStorage = function () {
        var json = localStorage.getItem("cart");
        $scope.cartItems = json ? JSON.parse(json) : [];

    }





});