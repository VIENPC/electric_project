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
    };
    window.onload = function () {
        // Lấy đoạn cuối của URL (được kỳ vọng là itemId)
        var productID = window.location.pathname.split('/').pop();
        console.log('productID:', productID);

        if (productID) {
            $scope.loadItemDetails(productID);
        }
    };

    $scope.goToProduct_Detail = function (productID) {
        window.location.href = `/detail/${productID}`;
    };
    $scope.calculateDiscountedPrice = function (product) {
        if (product.promotion) {
            return product.price * (1 - (product.promotion.discountPercent / 100));
        }
        return product.price;

    };
    $scope.cartItems = [];
    $scope.qty = 1;
    $scope.addToCartQty = function (masp) {

        var cart = this.cartItems.find(cart => cart.productID == masp);
        if (cart) {
            cart.qty += $scope.qty;
            $scope.saveToLocalStorage();
        } else {
            var url = `/rest/product/${masp}`;
            $http.get(url).then(resp => {
                resp.data.qty = $scope.qty;
                $scope.cartItems.push(resp.data);
                $scope.saveToLocalStorage();

            })
        }
        swal("Thành công", "Thêm sản phẩm vào giỏ hàng thành công!", "success").then(function () {

            location.reload();
        })


        //alert(qty);
    }
    $scope.saveToLocalStorage = function () {
        var json = JSON.stringify(angular.copy($scope.cartItems));
        localStorage.setItem("cart", json);
    }
    $scope.loadLocalStorage = function () {
        var json = localStorage.getItem("cart");
        $scope.cartItems = json ? JSON.parse(json) : [];

    }
    $scope.loadLocalStorage();

});