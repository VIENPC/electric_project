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

    $scope.addSupplier = function () {
        $http.post('/admin/createSupplier', $scope.newSup)
            .then(function (response) {
                $scope.suppliers.push(response.data);;
                $scope.newSup = {};
                swal("Thành công", "Thêm nhà sản xuẩt thành công!", "success")
            });
    };
    $scope.updateSupplier = function () {
        // Thực hiện kiểm tra form có hợp lệ hay không
        if ($scope.form.$invalid) {
            // Hiển thị thông báo lỗi nếu form không hợp lệ
            swal("Thất bại", "Vui lòng nhập vào để thay đổi!", "error")
            return;
        }

        // Gọi API để cập nhật thông tin item
        $http.put(`/adminUpdateSupplier/${$scope.newSup.supplierID}`, $scope.newSup)
            .then(function (response) {
                // Tìm và cập nhật thông tin người dùng trong mảng $scope.users
                var index = $scope.suppliers.findIndex(sup => sup.supplierID === $scope.newSup.supplierID);
                if (index !== -1) {
                    $scope.suppliers[index] = response.data;
                }
                $scope.newSup = {};
                swal("Thành công", "Cập nhật nhà sản xuất thành công!", "success")
            })
            .catch(function (error) {
                // Xử lý lỗi nếu có
                swal("Thất bại", "Cập nhật nhà sản xuất không thành công!", "error")
                console.error(error);
            });
    };
    $scope.deleteSupplier = function (supplier) {
        // Xác nhận người dùng muốn cập nhật trạng thái active về false
        if (confirm("Bạn có chắc chắn muốn xóa loại này?")) {
            supplier.active = false;

            // Gọi API để cập nhật trạng thái active của người dùng
            $http.put(`/deleteSupplier/${supplier.supplierID}`, supplier)
                .then(function (response) {
                    // Cập nhật thông tin người dùng trong mảng $scope.users
                    var index = $scope.suppliers.findIndex(u => u.supplierID === supplier.supplierID);
                    if (index !== -1) {
                        $scope.suppliers[index] = response.data;
                        swal("Thành công", "Xóa nhấ sản xuất thành công!", "success")
                    }
                })
                .catch(function (error) {
                    // Xử lý lỗi nếu có
                    swal("Thất bại", "Xóa không thành công!", "error")
                    console.error(error);
                    // Phục hồi trạng thái active về true nếu xảy ra lỗi
                    category.active = true;
                });
        }
    };




    // Hàm xử lý sự kiện chuyển về Tab 2
    $scope.switchToTab2 = function () {
        $('#myTabs a[href="#tab2"]').tab('show');
    };

    $scope.addProduct = function () {
        // Lấy giá trị từ CKEditor và gán vào newProduct.configuration
        var editor = CKEDITOR.instances.configuration;
        $scope.newProduct.configuration = editor.getData();

        // Lấy giá trị từ CKEditor cho trường description và gán vào newProduct.description
        var descriptionEditor = CKEDITOR.instances.description;
        $scope.newProduct.description = descriptionEditor.getData();

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

    $scope.updateProduct = function () {
        // Lấy giá trị từ CKEditor và gán vào newProduct.configuration
        var editor = CKEDITOR.instances.configuration;
        $scope.newProduct.configuration = editor.getData();

        // Lấy giá trị từ CKEditor cho trường description và gán vào newProduct.description
        var descriptionEditor = CKEDITOR.instances.description;
        $scope.newProduct.description = descriptionEditor.getData();

        // Thực hiện kiểm tra form có hợp lệ hay không
        if ($scope.form.$invalid) {
            // Hiển thị thông báo lỗi nếu form không hợp lệ
            $scope.alertWaring("Vui lòng điền đầy đủ thông tin hợp lệ.");
            return;
        }

        // Gọi API để cập nhật thông tin item
        $http.put(`/adminUpdateProduct/${$scope.newProduct.productID}`, $scope.newProduct)
            .then(function (response) {
                // Tìm và cập nhật thông tin sản phẩm trong mảng $scope.products
                var index = $scope.products.findIndex(product => product.productID === $scope.newProduct.productID);
                if (index !== -1) {
                    $scope.products[index] = response.data;
                }
                // Xóa thông tin sản phẩm trong biểu mẫu sau khi cập nhật thành công
                $scope.newProduct = {};
                swal("Thành công", "Cập nhật sản phẩm thành công!", "success");
            })
            .catch(function (error) {
                // Xử lý lỗi nếu có
                swal("Thất bại", "Cập nhật sản phẩm thất bại!" + error.statusText, "error");
                console.error(error);
            });
    };

    $scope.deleteProduct = function (product) {
        // Xác nhận người dùng muốn cập nhật trạng thái active về false
        if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
            product.active = false;

            // Gọi API để cập nhật trạng thái active của người dùng
            $http.put(`/deleteProduct/${product.productID}`, product)
                .then(function (response) {
                    // Cập nhật thông tin người dùng trong mảng $scope.users
                    var index = $scope.products.findIndex(u => u.productID === product.productID);
                    if (index !== -1) {
                        $scope.products[index] = response.data;
                        swal("Thành công", "Xóa sản phẩm thành công!", "success")
                    }
                })
                .catch(function (error) {
                    // Xử lý lỗi nếu có
                    swal("Thất bại", "Xóa không thành công!", "error")
                    console.error(error);
                    // Phục hồi trạng thái active về true nếu xảy ra lỗi
                    product.active = true;
                });
        }
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

    $scope.saveToDatabase = function () {
        var json = localStorage.getItem("cart");
        var cartItems = json ? JSON.parse(json) : [];

        var productData = cartItems.map(function (item) {
            return { productId: item.productID, quantity: item.qty };
        });
        console.log("thông tin dữ liệu", productData);
        $http.post('/rest/cartdetail', productData)
            .then(function (response) {
                // Xử lý kết quả sau khi lưu vào cơ sở dữ liệu thành công
                console.log('Mã sản phẩm đã được lưu vào cơ sở dữ liệu.');
            })
            .catch(function (error) {
                // Xử lý lỗi nếu có
                console.error('Lỗi khi lưu mã sản phẩm vào cơ sở dữ liệu:', error);
            });
    };


    $scope.saveToDatabase();





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
    $scope.account = []
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
                            orderid: order_id,
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



                    } else {
                        console.log("thanh công", resp);

                        swal("Thành Công", "Đặt hàng thành công", "success")
                    }
                    $scope.clear(); l

                },
                (error) => {
                    alert("Đặt hàng lỗi!");
                    console.log("Error", error);
                }
            );


        },
    };

});

