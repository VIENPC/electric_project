var app = angular.module('my-app');

app.controller('product-controller', function ($scope, $http, $window, $sce) {
    $scope.products = [];
    $scope.productSort = [];
    $scope.brands = [];
    $scope.products = {};
    $scope.categoris = [];
    $scope.suppliers = [];
    $scope.newProduct = {};
    $scope.form = {};
    $scope.noProductsFound = false; // Thêm biến để kiểm tra xem có sản phẩm nào được tìm thấy hay không
    $scope.showAllProducts = false;
    $scope.selectedCategory = null; // Biến để theo dõi checkbox được chọn
    $scope.calculateDiscountedPrice = 0;
    $scope.productsPerPage = 3; // Số lượng sản phẩm hiển thị ban đầu
    $scope.productsToShow = $scope.productsPerPage;

    $scope.selectedProducts = [];
    $scope.isProductDialogVisible = false;

    $scope.selectProduct = function (product) {
        if ($scope.selectedProducts.length < 2) {
            $scope.selectedProducts.push(product);
            $scope.showSearchResults = true; // Hiển thị kết quả nếu tìm thấy sản phẩm
            $('#exampleModal').modal('show');
            $('#exampleModal').on('shown.bs.modal', function () {
                $('body').addClass('modal-open');
            });

            $('#exampleModal').on('hidden.bs.modal', function () {
                $('body').removeClass('modal-open');
            });

        } else {
            alert('Chỉ được chọn hai sản phẩm để so sánh.');
        }
    };

    $scope.closeProductDialog = function () {
        $scope.isProductDialogVisible = false;
    };

    $scope.firstColumnValues = [
        'Tên sản phẩm',
        'Giá',
        'Loại',
        'Thương hiệu',
        'Cấu hình'
        // Thêm các giá trị khác tương ứng với 7 dòng
    ];

    $scope.compareProducts = function () {
        $scope.showSearchResults = true; // Hiển thị kết quả nếu tìm thấy sản phẩm
        $('#exampleModal1').modal('show');
        $('#exampleModal1').on('shown.bs.modal', function () {
            $('body').addClass('modal-open');
        });

        $('#exampleModal1').on('hidden.bs.modal', function () {
            $('body').removeClass('modal-open');
            $scope.resetSelectedProducts(); // Gọi hàm để xóa danh sách sản phẩm đã chọn

        });
        if ($scope.selectedProducts.length !== 2) {
            return;
        }

        const product1 = $scope.selectedProducts[0];
        const product2 = $scope.selectedProducts[1];

        const attributesToCompare = ['productName', 'price', 'category', 'brand', 'configuration'];

        const comparisonResult = [];

        for (const attribute of attributesToCompare) {
            const product1Value = product1[attribute];
            const product2Value = product2[attribute];
            if (attribute === 'brand') {
                // Đổi thuộc tính 'brand' thành 'brandName'
                const brand1Name = product1.brand.brandName;
                const brand2Name = product2.brand.brandName;
                const result = {
                    attribute: 'brandName',
                    product1Value: brand1Name,
                    product2Value: brand2Name,
                };
                comparisonResult.push(result);
            } else if (attribute === 'category') {
                const category1Name = product1.category.categoryName;
                const category2Name = product2.category.categoryName;
                const result = {
                    attribute: 'categoryName',
                    product1Value: category1Name,
                    product2Value: category2Name,
                };
                comparisonResult.push(result);
            } else if (attribute === 'price') {
                // Định dạng giá thành tiền Việt (VND)
                const price1VND = product1Value.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                const price2VND = product2Value.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                const result = {
                    attribute: 'price',
                    product1Value: price1VND,
                    product2Value: price2VND,
                };
                comparisonResult.push(result);
            } else if (attribute === 'configuration') {
                // Hiển thị mô tả dưới dạng mã HTML
                const result = {
                    attribute: attribute,
                    product1Value: $sce.trustAsHtml(product1Value),
                    product2Value: $sce.trustAsHtml(product2Value),
                };
                comparisonResult.push(result);
            } else {
                const result = {
                    attribute: attribute,
                    product1Value: product1Value,
                    product2Value: product2Value,
                };
                comparisonResult.push(result);
            }
        }

        $scope.comparisonResult = comparisonResult;
    };


    $scope.resetSelectedProducts = function () {
        $scope.selectedProducts = [];
    };

    $scope.showMoreProducts = function () {
        $scope.productsToShow += $scope.productsPerPage;
    };

    $scope.showLessProducts = function () {
        $scope.productsToShow = $scope.productsPerPage;
    };


    $scope.productsListPerPage = 9; // Số lượng sản phẩm hiển thị ban đầu
    $scope.productsToShowList = $scope.productsListPerPage;

    $scope.showMoreProductsList = function () {
        $scope.productsToShowList += $scope.productsListPerPage;
    };

    $scope.showLessProductsList = function () {
        $scope.productsToShowList = $scope.productsListPerPage;
    };



    $http.get('/rest/product')
        .then(function (response) {
            $scope.products = response.data;
            $scope.allProducts = response.data;

        });

    $scope.calculateDiscountedPrice = function (product) {
        if (product.promotion) {
            return product.price * (1 - (product.promotion.discountPercent / 100));
        }
        return product.price;

    };

    $http.get('/rest/productSort')
        .then(function (response) {
            // Xử lý dữ liệu trả về từ API
            var productSort = response.data;

            // Lọc và sắp xếp danh sách sản phẩm
            var discountedProducts = productSort.filter(function (product) {
                return product.promotion !== null;
            });

            discountedProducts.sort(function (a, b) {
                return b.price - a.price;
            });

            // Lấy ra 8 sản phẩm đầu tiên
            var top8DiscountedProducts = discountedProducts.slice(0, 8);

            // Gán danh sách sản phẩm đã xử lý cho $scope
            $scope.productSort = top8DiscountedProducts;
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

    $scope.switchToTab2 = function () {
        $('#myTabs a[href="#tab2"]').tab('show');
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

    $scope.showAllProductsChanged = function () {
        if ($scope.showAllProducts) {
            // Nếu checkbox "Hiển thị tất cả sản phẩm" được chọn, hiển thị tất cả sản phẩm
            $scope.products = $scope.allProducts;
            $scope.selectedCategory = null; // Hủy chọn checkbox khác nếu có
        } else {
            // Ngược lại, áp dụng bộ lọc thông qua các checkbox khác
            $scope.filterData();

        }
    };

    $scope.selectedCategory = null;

    $scope.toggleSelection = function (selectedItem) {
        if (selectedItem.selected) {
            // Nếu một checkbox được chọn, hủy chọn các checkbox khác
            $scope.categoris.forEach(function (item) {
                if (item !== selectedItem) {
                    item.selected = false;
                }
            });
            $scope.selectedCategory = selectedItem.categoryID;
            $scope.filterData(selectedItem.categoryID);
        } else if (selectedItem.categoryID === $scope.selectedCategory) {
            // Nếu checkbox được bỏ chọn và là checkbox hiện đang được chọn, tải lại trang
            location.reload();
        }
    };

    // Hàm để lọc và tải sản phẩm và brand theo categoryID đã chọn
    $scope.filterData = function (categoryID) {
        // Tải sản phẩm dựa trên categoryID
        $http.get('/products-by-category/' + categoryID)
            .then(function (response) {
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

        // Tải brand dựa trên categoryID
        $http.get('/brands-by-category/' + categoryID)
            .then(function (response) {
                if (response.data.length === 0) {
                    $scope.noProductsFound = true; // Hiển thị thông báo nếu không có sản phẩm nào được tìm thấy
                } else {
                    $scope.noProductsFound = false; // Ẩn thông báo nếu có sản phẩm được tìm thấy
                }
                $scope.brands = filterUniqueBrands(response.data);
            })
            .catch(function (error) {
                console.error('Error fetching brands:', error);
            });
    };
    $scope.reloadPageIfUnselected = function (selected) {
        if (!selected) {
            // Nếu checkbox được bỏ chọn (selected = false), thực hiện tải lại trang
            location.reload();
        }
    };

    $scope.filterDataBrand = function (brandID) {

        // Tải loại dựa trên brandID
        $http.get('/categories-by-brand/' + brandID)
            .then(function (response) {
                if (response.data.length === 0) {
                    $scope.noProductsFound = true; // Hiển thị thông báo nếu không có sản phẩm nào được tìm thấy
                } else {
                    $scope.noProductsFound = false; // Ẩn thông báo nếu có sản phẩm được tìm thấy
                }
                $scope.categoris = filterUniqueCategories(response.data);
            })
            .catch(function (error) {
                console.error('Error fetching categories:', error);
            });

        // Tải sản phẩm dựa trên brandID
        $http.get('/products-by-brand/' + brandID)
            .then(function (response) {
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

    $scope.loadProductsByCategory = function (categoryID) {
        $http.get('/rest/products-by-category/' + categoryID)
            .then(function (response) {
                $scope.products = filterUniqueCategories(response.data);
            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };
    // Hàm lọc danh sách brand để chỉ hiển thị một brand duy nhất nếu có
    function filterUniqueBrands(brands) {
        const uniqueBrands = [];
        const brandMap = {};

        brands.forEach(brand => {
            if (!brandMap[brand.brandName]) {
                uniqueBrands.push(brand);
                brandMap[brand.brandName] = true;
            }
        });

        return uniqueBrands;
    }
    // Hàm lọc danh sách loại sản phẩm để chỉ hiển thị một category duy nhất nếu có
    function filterUniqueCategories(categories) {
        const uniqueCategories = [];
        const categoryMap = {};

        categories.forEach(category => {
            if (!categoryMap[category.categoryID]) {
                uniqueCategories.push(category);
                categoryMap[category.categoryID] = true;
            }
        });

        return uniqueCategories;
    }
    // Sử dụng sự kiện window.onhashchange để theo dõi thay đổi fragment
    window.onhashchange = function () {
        // Lấy fragment từ URL
        var fragment = window.location.hash.substr(2); // Loại bỏ dấu "#"
        console.log('Fragment:', fragment);
        if (fragment) {
            $scope.$apply(function () {
                $scope.loadProductsByCategory(fragment);
            });
        }
    };
    // Gọi $scope.loadProductsByCategory khi trang được nạp ban đầu
    window.onload = function () {
        var fragment = window.location.hash.substr(2); // Loại bỏ dấu "#"
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



    // }
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
        return $scope.calculateDiscountedPrice(cart) * cart.qty;
    }
    $scope.totalAmount = function () {
        return $scope.cartItems
            .map(cart => this.amt_of(cart))
            .reduce((total, amt) => total += amt, 0);
    };



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
                // resp.data.giagiam = $scope.calculateDiscountedPrice();
                $scope.cartItems.push(resp.data);
                // Cập nhật biến trong $scope
                $scope.saveToLocalStorage();
                // $scope.cartItemCount = $scope.getcount();

            })
        }
        swal("Thành công", "Thêm sản phẩm vào giỏ hàng thành công!", "success").then(function () {

            location.reload();
        })
        // Bắt buộc AngularJS cập nhật giao diện
    }
    $scope.loadLocalStorage();
    $scope.getcount();

});
app.controller("checkctrl", function ($scope, $http, $filter) {
    $scope.vouchers = [];
    $scope.noVouchersFound = false; // Mặc định không có thông báo khi không có voucher
    $scope.cartItems = [];


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
    $scope.getcount = function () { // tính tổng số lượng các mặt hàng trong giỏ
        return $scope.cartItems
            .map(cart => cart.qty)
            .reduce((total, qty) => total += qty, 0);

    }
    $scope.amt_of = function (cart) { // tính thành tiền của 1 sản phẩm
        return $scope.calculateDiscountedPrice(cart) * cart.qty;
    }
    $scope.totalAmount = function () {
        return $scope.cartItems
            .map(cart => this.amt_of(cart))
            .reduce((total, amt) => total += amt, 0);
    };
    $scope.tiengiam = 0;
    $scope.tongtien = 0;

    $scope.loadLocalStorage = function () {
        var json = localStorage.getItem("cart");
        $scope.cartItems = json ? JSON.parse(json) : [];
        $scope.getcount();
        $scope.tongtien = $scope.totalAmount();
        console.log("tong tien hoa don", $scope.tongtien);
    }


    $scope.calculateDiscountedPrice = function (product) {
        if (product.promotion) {
            return product.price * (1 - (product.promotion.discountPercent / 100));
        }
        return product.price;

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
    $scope.noVouchersFound = true; // Ban đầu giả sử không có voucher phù hợp
    $scope.getAccount();

    $http.get('/rest/promotion')
        .then(function (response) {// Sau khi tải danh sách vouchers
            $scope.vouchers = response.data;
        });


    $scope.checkVouchers = function () {
        $scope.noVouchersFound = true; // Đặt giả định ban đầu là không có voucher

        // Kiểm tra danh sách vouchers để xem có voucher nào phù hợp không
        for (var i = 0; i < $scope.vouchers.length; i++) {
            var item = $scope.vouchers[i];

            if (item.minOrderAmount <= totalAmount() && item.status == true && item.olduser == false) {
                $scope.noVouchersFound = false; // Tìm thấy ít nhất một voucher phù hợp
                break; // Dừng kiểm tra khi tìm thấy một voucher
            }
        }
    };

    // Gọi hàm checkVouchers để kiểm tra khi trang được tải
    $scope.checkVouchers();

    $scope.loadId = function (itemId) {
        $http.get('/rest/' + itemId)
            .then(function (response) {
                $scope.tongtien = 0;
                $scope.tiengiam = ($scope.totalAmount() * response.data.discountPercent / 100);
                console.log("coaihd", $scope.tiengiam);
                $scope.tongtien = $scope.totalAmount() - $scope.tiengiam;

            })
            .catch(function (error) {
                console.error('Error fetching products:', error);
            });
    };





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
        totalAmount: 0,
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
            order.totalAmount = $scope.tongtien;
            // console.log("Tongtien",);



            $http.post(url, order).then(
                (resp) => {
                    var order_id = resp.data.orderId
                    console.log("tong tien hoa don", $scope.totalAmount())
                    if ($scope.selectedPaymentMethod == "VNPAY") {
                        datased = {
                            amount: $scope.tongtien,
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
                        swal("Thành Công", "Đặt hàng thành công", "success")
                        $scope.clear();
                        window.location.href = `/shop`;
                    }
                },
                (error) => {
                    alert("Đặt hàng lỗi!");
                    console.log("Error", error);
                }
            );
        },
    };
});

