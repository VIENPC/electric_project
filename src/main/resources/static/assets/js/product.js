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
    $scope.goToProduct_Detail = function (productID) {
        window.location.href = `/detail/${productID}`;
    };

    $scope.loadProductsByBrand = function (brandID) {
        $http.get('/rest/products-by-brand?brandID=' + brandID)
            .then(function (response) {
                console.log(brandID)
                $scope.products = response.data;
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };

    $scope.loadProductsByCategory = function (categoryID) {
        $http.get('/rest/products-by-category/' + categoryID)
            .then(function (response) {
                $scope.products = response.data;
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };

    // Sử dụng sự kiện window.onhashchange để theo dõi thay đổi fragment
    window.onhashchange = function () {
        // Lấy fragment từ URL
        var fragment = window.location.hash.substr(1); // Loại bỏ dấu "#"
        console.log('Fragment:', fragment);
        if (fragment) {
            $scope.$apply(function () {
                $scope.loadProductsByCategory(fragment);
            });
        }
    };

    // Gọi $scope.loadProductsByCategory khi trang được nạp ban đầu
    window.onload = function () {
        var fragment = window.location.hash.substr(1); // Loại bỏ dấu "#"
        console.log('Fragment:', fragment);
        if (fragment) {
            $scope.$apply(function () {
                $scope.loadProductsByCategory(fragment);
            });
        }
    };


    $scope.addToCart = function (masp) {
        alert(masp)
        var cart = this.products.find(cart => cart.masp == masp);
        $scope.cartItems = [];
        $scope.addToCart = function (masp) {

            var cart = this.cartItems.find(cart => cart.productID == masp);
            if (cart) {

                cart.qty++;
                $scope.saveToLocalStorage();

            } else {
                var url = `/rest/product/${masp}`;
                $http.get(url).then(resp => {
                    resp.data.qty = 1;
                    $scope.cartItems.push(resp.data);
                    $scope.saveToLocalStorage();

                })
            }

            swal("Thành công", "Thêm sản phẩm vào giỏ hàng thành công!", "success")


        }
        $scope.saveToLocalStorage = function () {
            var json = JSON.stringify(angular.copy($scope.cartItems));
            localStorage.setItem("cart", json);
        }
        $scope.loadLocalStorage = function () {
            var json = localStorage.getItem("cart");
            $scope.cartItems = json ? JSON.parse(json) : [];

        }
        $scope.removesp = function (masp) {
            swal({
                title: "Cảnh báo",
                text: "Bạn có chắc chắn muốn xóa sản phẩm này?",
                buttons: ["Hủy bỏ", "Đồng ý"],
                icon: "warning"
            }).then((willDelete) => {
                if (willDelete) {
                    var index = $scope.cartItems.findIndex(cart => cart.masp == masp);
                    $scope.cartItems.splice(index, 1);
                    $scope.saveToLocalStorage();
                    swal("Thành công", "Sản phẩm đã được xóa!", "success")

                    $scope.$apply();
                }
            })

        }
        $scope.getcount = function () { // tính tổng số lượng các mặt hàng trong giỏ
            return $scope.cartItems
                .map(cart => cart.qty)
                .reduce((total, qty) => total += qty, 0);

        }
        $scope.amt_of = function (cart) { // tính thành tiền của 1 sản phẩm
            return cart.price * cart.qty;
        }
        $scope.totalAmount = function () {
            return $scope.cartItems
                .map(cart => this.amt_of(cart))
                .reduce((total, amt) => total += amt, 0);
        };
        $scope.loadLocalStorage();
        // Quan ly gio hang
        $scope.cartItems = [];
        $scope.addToCart = function (masp) {

            var cart = this.cartItems.find(cart => cart.productID == masp);
            if (cart) {

                cart.qty++;
                $scope.saveToLocalStorage();
            } else {
                var url = `/rest/product/${masp}`;
                $http.get(url).then(resp => {
                    resp.data.qty = 1;
                    $scope.cartItems.push(resp.data);
                    $scope.saveToLocalStorage();

                })
            }

            swal("Thành công", "Thêm sản phẩm vào giỏ hàng thành công!", "success")


        }
        $scope.saveToLocalStorage = function () {
            var json = JSON.stringify(angular.copy($scope.cartItems));
            localStorage.setItem("cart", json);
        }
        $scope.loadLocalStorage = function () {
            var json = localStorage.getItem("cart");
            $scope.cartItems = json ? JSON.parse(json) : [];

        }
        $scope.removesp = function (masp) {
            swal({
                title: "Cảnh báo",
                text: "Bạn có chắc chắn muốn xóa sản phẩm này?",
                buttons: ["Hủy bỏ", "Đồng ý"],
                icon: "warning"
            }).then((willDelete) => {
                if (willDelete) {
                    var index = $scope.cartItems.findIndex(cart => cart.productID == masp);
                    $scope.cartItems.splice(index, 1);
                    $scope.saveToLocalStorage();
                    swal("Thành công", "Sản phẩm đã được xóa!", "success")

                    $scope.$apply();
                }
            })

        }
        $scope.getcount = function () { // tính tổng số lượng các mặt hàng trong giỏ
            return $scope.cartItems
                .map(cart => cart.qty)
                .reduce((total, qty) => total += qty, 0);

        }
        $scope.amt_of = function (cart) { // tính thành tiền của 1 sản phẩm
            return cart.price * cart.qty;
        }
        $scope.totalAmount = function () {
            return $scope.cartItems
                .map(cart => this.amt_of(cart))
                .reduce((total, amt) => total += amt, 0);
        };
        $scope.loadLocalStorage();




    }
});