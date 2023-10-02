var app = angular.module('my-app');

app.controller('product-controller', function ($scope, $http, $window, $sce) {
    $scope.products = [];
    $scope.brands = [];
    $scope.products = {};
    $scope.categoris = [];
    $scope.suppliers = [];
    $scope.newProduct = {};
    $scope.form = {};
    $scope.noProductsFound = false; // Thêm biến để kiểm tra xem có sản phẩm nào được tìm thấy hay không

    $http.get('/rest/product')
        .then(function (response) {
            $scope.products = response.data;
            $scope.allProducts = response.data;
        });
    $http.get('/rest/supplier')
        .then(function (response) {
            $scope.suppliers = response.data;
        });

    $http.get('/rest/brand')
        .then(function (response) {
            $scope.brands = response.data;
        });
    $http.get('/rest/category')
        .then(function (response) {
            $scope.categoris = response.data;
        });

    $scope.edit = function (products) {
        $scope.newProduct = angular.copy(products);
        $scope.switchToTab2();
    }
    // Hàm xử lý sự kiện chuyển về Tab 1
    $scope.switchToTab2 = function () {
        $('#myTabs a[href="#tab2"]').tab('show');
    };

    $scope.addProduct = function () {
        // Lấy giá trị từ CKEditor và gán vào newProduct.configuration
        var editor = CKEDITOR.instances.configuration;
        $scope.newProduct.configuration = editor.getData();

        // Tiếp tục với phần còn lại của hàm
        $http.post('/admin/createProduct', $scope.newProduct)
            .then(function (response) {
                $scope.products.push(response.data);
                $scope.newProduct = {};
                swal("Thành công", "Thêm sản phẩm thành công!", "success");
            })
            .catch(function (error) {
                // Xử lý lỗi nếu có
                console.error('Lỗi khi thêm sản phẩm: ' + error);
            });
    };
    $scope.trustedHtml = function (htmlCode) {
        return $sce.trustAsHtml(htmlCode);
    };


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
                if (response.data.length === 0) {
                    $scope.noProductsFound = true; // Hiển thị thông báo nếu không có sản phẩm nào được tìm thấy
                } else {
                    $scope.noProductsFound = false; // Ẩn thông báo nếu có sản phẩm được tìm thấy
                }
                $scope.products = response.data;
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };

    $scope.showAllProducts = false;
    $scope.selectedCategory = null; // Biến để theo dõi checkbox được chọn

    $scope.showAllProductsChanged = function () {
        if ($scope.showAllProducts) {
            // Nếu checkbox "Hiển thị tất cả sản phẩm" được chọn, hiển thị tất cả sản phẩm
            $scope.products = $scope.allProducts;
            $scope.selectedCategory = null; // Hủy chọn checkbox khác nếu có
        } else {
            // Ngược lại, áp dụng bộ lọc thông qua các checkbox khác
            $scope.filterProducts();
        }
    };

    $scope.filterProducts = function () {
        $scope.products = [];

        // Biến để kiểm tra xem có ít nhất một checkbox được chọn hay không
        var atLeastOneSelected = false;

        // Lặp qua danh sách loại sản phẩm và tải sản phẩm nếu checkbox được chọn
        angular.forEach($scope.categoris, function (category) {
            if (category.selected) {
                atLeastOneSelected = true; // Đánh dấu là có ít nhất một checkbox được chọn
                $http.get('/rest/products-by-category/' + category.categoryID)
                    .then(function (response) {
                        // Nối sản phẩm tải được vào danh sách filteredProducts
                        $scope.products = $scope.products.concat(response.data);

                        // Kiểm tra xem có sản phẩm phù hợp hay không để cập nhật thông báo
                        if ($scope.products.length === 0) {
                            $scope.noProductsFound = true; // Hiển thị thông báo nếu không có sản phẩm nào được tìm thấy
                        } else {
                            $scope.noProductsFound = false; // Ẩn thông báo nếu có sản phẩm được tìm thấy
                        }
                    })
                    .catch(function (error) {
                        console.error('Error fetching products:', error);
                    });
            }
        });

        // Nếu không có checkbox nào được chọn, hiển thị tất cả sản phẩm
        if (!atLeastOneSelected) {
            $scope.products = $scope.allProducts;
            $scope.noProductsFound = false; // Ẩn thông báo
        }
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

    $scope.selectedPrice = '';


    $scope.loadProductsByPrice = function (price) {
        $http.get('/rest/products-by-price?price=' + price)
            .then(function (response) {
                if (response.data.length === 0) {
                    $scope.noProductsFound = true; // Hiển thị thông báo nếu không có sản phẩm nào được tìm thấy
                } else {
                    $scope.noProductsFound = false; // Ẩn thông báo nếu có sản phẩm được tìm thấy
                }

                $scope.products = response.data;
            })
            .catch(function (error) {
                console.error('Error fetching products by price:', error);
            });
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

    $scope.imageChanged2 = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/uploads/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.newProduct.image = resp.data.name;
        }).catch(error => {
            alert("Loi roi");
            console.log("Error:" + error);
        })
    }

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

        $http.get('/api/account/login').then(resp => {
            $scope.account = resp.data;
            console.log("account", resp)


        }).catch(error => {
            console.log("Error", error)
        });

        // $http.get('/rest/account').then(resp => {
        //     $scope.account = resp.data;
        //     console.log("accountthuong", resp)


        // }).catch(error => {
        //     console.log("Error", error)
        // });


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
        user: {}, name: "", // Thêm trường tên người nhận
        address: "",
        phone: "",
        statushd: 1,
        statustt: 1,
        totalAmount: $scope.totalAmount(),
        // note: "Trống", // Thêm trường số điện thoại người nhận
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
            var url = '/rest/hoadon';
            var user = {
                userID: $scope.account.userID,

            };

            order.name = $scope.account.fullName;
            order.user = user;
            order.phone = $scope.account.phoneNumber;
            order.address = $scope.dnct + ',' + diachinn1;
            order.note = $scope.ghichu;
            console.log("thông tin", user);


            $http.post(url, order).then(
                (resp) => {

                    $scope.clear(); // Xóa giỏ hàng sau khi đặt hàng thành công
                    swal("Thành Công", "Đặt hàng thành công", "success").then(function () {
                        window.location.href = "";
                    });
                },
                (error) => {
                    alert("Đặt hàng lỗi!");
                    console.log("Error", error);
                }
            );
        },
    };







});