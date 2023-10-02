var app = angular.module('my-app');

app.controller('product-controller', function ($scope, $http, $window) {
    $scope.products = [];
    $scope.brands = [];
    $scope.products = {};
    $scope.categoris = [];


    $http.get('/rest/product')
        .then(function (response) {
            $scope.products = response.data;
        });
    $http.get('/rest/brand')
        .then(function (response) {
            $scope.brands = response.data;
        });
    $http.get('/rest/category')
        .then(function (response) {
            $scope.categoris = response.data;
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

    // Hàm để lọc sản phẩm dựa trên các checkbox đã chọn và tải sản phẩm theo categoryID
    $scope.filterProducts = function (categoryID) {
        $scope.products = [];

        // Lặp qua danh sách loại sản phẩm và tải sản phẩm nếu checkbox được chọn
        angular.forEach($scope.categoris, function (category) {
            if (category.selected) {
                $http.get('/rest/products-by-category/' + category.categoryID)
                    .then(function (response) {
                        // Nối sản phẩm tải được vào danh sách filteredProducts
                        $scope.products = $scope.products.concat(response.data);
                    })
                    .catch(function (error) {
                        console.error('Error fetching products:', error);
                    });
            }
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
    // Khởi tạo showSearchResults là false ban đầu
    $scope.showSearchResults = false;

    // Hàm tìm kiếm sản phẩm theo tên
    $scope.searchProductsByName = function () {
        $http.get('/rest/product-search?productName=' + $scope.searchText)
            .then(function (response) {
                $scope.products = response.data;
                if ($scope.products.length === 0) {
                    swal("Thất bại", "Không tìm thấy sản phẩm!", "error");
                    $scope.showSearchResults = false; // Ẩn kết quả nếu không tìm thấy sản phẩm
                } else {
                    $scope.showSearchResults = true; // Hiển thị kết quả nếu tìm thấy sản phẩm
                }
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
                $scope.showSearchResults = false; // Ẩn kết quả nếu có lỗi
            });
    };
    // Hàm để đóng kết quả tìm kiếm
    $scope.closeSearchResults = function () {
        $scope.showSearchResults = false;
    };

    $scope.priceRanges = [
        { min: 0, max: 5000000, label: "Dưới 5.000.000 VNĐ" },
        { min: 15000000, max: 200000000, label: "15.000.000-20.000.000 VNĐ" },
        { min: 20000000, max: 50000000, label: "20.000.000-50.000.000 VNĐ" },
        // Thêm các mốc giá khác tùy ý
    ];
    $scope.selectedPriceRange = $scope.priceRanges[0];
    $scope.filterItemsByPrice = function () {
        var minPrice = $scope.selectedPriceRange.min;
        var maxPrice = $scope.selectedPriceRange.max;

        $http.get('/rest/filterByPrice', { params: { minPrice: minPrice, maxPrice: maxPrice } })
            .then(function (response) {

                $scope.products = response.data;
                if ($scope.items.length === 0) {
                    swal("Thất bại", "Không tìm thấy sản phẩm trong tầm giá!", "error")
                    return;
                }
                swal("Thành công", "Thêm sản phẩm vào giỏ hàng thành công!", "success")
            })
            .catch(function (error) {
                console.error('Error while filtering items:', error);
                swal("Thất bại", "Đã có lổi xãy ra!", "error")
            });
    };
    // Tự động gọi hàm lọc sản phẩm khi mốc giá thay đổi
    $scope.$watch('selectedPriceRange', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.filterItemsByPrice();
        }
    });


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





});
app.controller("checkctrl", function ($scope, $http, $filter) {
    const host = "https://provinces.open-api.vn/api/";
    var callAPI = (api) => {
        return axios.get(api)
            .then((response) => {
                renderData(response.data, "province");
            });
    }
    callAPI('https://provinces.open-api.vn/api/?depth=1');
    var callApiDistrict = (api) => {
        return axios.get(api)
            .then((response) => {
                renderData(response.data.districts, "district");
            });
    }
    var callApiWard = (api) => {
        return axios.get(api)
            .then((response) => {
                renderData(response.data.wards, "ward");
            });
    }

    var renderData = (array, select) => {
        let row = ' <option disable value="">Chọn</option>';
        array.forEach(element => {
            row += `<option value="${element.code}">${element.name}</option>`
        });
        document.querySelector("#" + select).innerHTML = row
    }
    $scope.diachinn = '';
    $("#province").change(() => {
        callApiDistrict(host + "p/" + $("#province").val() + "?depth=2");
        printResult();
    });
    $("#district").change(() => {
        callApiWard(host + "d/" + $("#district").val() + "?depth=2");
        printResult();
    });
    $("#ward").change(() => {
        printResult();

    })
    let diachinn1 = "";
    var printResult = () => {
        if ($("#district").val() != "" && $("#province").val() != "" &&
            $("#ward").val() != "") {
            let result = $("#ward option:selected").text() + ","
                + $("#district option:selected").text() + ","
                + $("#province option:selected").text();


            diachinn1 = result;
        }

    }

    $scope.loadLocalStorage = function () {
        var json = localStorage.getItem("cart");
        $scope.cartItems = json ? JSON.parse(json) : [];



    }
    $scope.amt_of = function (cart) { // tính thành tiền của 1 sản phẩm
        return cart.price * cart.qty;
    }
    $scope.totalAmount = function () {
        return $scope.cartItems
            .map(cart => this.amt_of(cart))
            .reduce((total, amt) => total += amt, 0);
    };

    $scope.getAccount = function () {

        $http.get('/rest/account').then(resp => {
            $scope.account = resp.data;
            console.log("account", resp)


        }).catch(error => {
            console.log("Error", error)
        });
    };
    $scope.getAccount();
    $scope.saveToLocalStorage = function () {
        var json = JSON.stringify(angular.copy($scope.cartItems));
        localStorage.setItem("cart", json);
    }
    $scope.clear = function () {

        $scope.cartItems = []
        $scope.saveToLocalStorage();
        // Xóa sạch các mặt hàng trong giỏ

    }

    $scope.loadLocalStorage();

    $scope.order = {
        orderDate: new Date(),
        user: {},
        name: "", // Thêm trường tên người nhận
        address: "",
        phone: "",
        statushd: 1,
        statustt: 0,
        totalAmount: $scope.totalAmount(),
        note: "Trống", // Thêm trường số điện thoại người nhận
        get orderDetails() {
            return $scope.cartItems.map(cart => {
                return {
                    product: { productID: cart.productID },
                    price: cart.price,
                    quantity: cart.qty,
                };
            });
        },
        purchase() {
            var order = angular.copy(this);
            var url = "/rest/hoadon";
            var user = {
                userID: $scope.account.userID,

            };

            order.name = $scope.account.fullName;
            order.user = user;
            order.phone = $scope.account.phoneNumber;
            order.address = $scope.dnct + ',' + diachinn1;
            order.note = $scope.ghichu;
            // console.log("thông tin",user);

            

            $http.post(url, order).then(
                (resp) => {
                    var order_id = resp.data.orderId
                    if ($scope.selectedPaymentMethod == "VNPAY") {
                        datased = {
                            amount: $scope.totalAmount(),
                            orderid : order_id,
                        }
                       
                        $http.post('/pay/vnpayajax', datased)
                            .then(function (response) {
                                var paymentUrl = response.data.paymentUrl;

                                //Thực hiện chuyển hướng đến paymentUrl
                                window.location.href = paymentUrl;
                                //console.error("tthong tin resposte", response);
                            })
                            .catch(function (error) {
                                // Xử lý lỗi nếu có
                                console.error("API Error:", error);
                            });



                    }else{
                        console.log("thanh công", resp);
                       
                        swal("Thành Công", "Đặt hàng thành công", "success")
                    }
                    $scope.clear();l
                   
                },
                (error) => {
                    alert("Đặt hàng lỗi!");
                    console.log("Error", error);
                }
            );


        },
    };

});

