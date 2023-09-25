
var app = angular.module("myapp", ["ngRoute", "ngCookies"]);


app.run(function ($rootScope) {
    $rootScope.$on('$routeChangeStart', function () {
        $rootScope.loading = true;
    });
    $rootScope.$on('$routeChangeSuccess', function () {
        $rootScope.loading = false;
    });
    $rootScope.$on('$routeChangeError', function () {
        $rootScope.loading = false;
        alert("Lỗi");
    });
});


let host = "http://localhost:8080/rest";


app.controller("watches", function ($scope, $http, $anchorScroll, $location) {


    $scope.muangay = function (masp) {
        var cart = this.cartItems.find(cart => cart.masp == masp);
        if (cart) {
            cart.qty++;
            $scope.saveToLocalStorage();
            window.location.href = "/cart";
        } else {
            var url = `${host}/sanpham/${masp}`;
            $http.get(url).then(resp => {
                resp.data.qty = 1;
                $scope.cartItems.push(resp.data);
                $scope.saveToLocalStorage();
                window.location.href = "/cart";
                $scope.cuonLenDauTrang();

            });


        }

    }
    //thêm sản phẩm vào giỏ hàng

    $scope.cartItems = [];
    $scope.addToCart = function (masp) {
        var cart = this.cartItems.find(cart => cart.masp == masp);
        if (cart) {
            cart.qty++;
            $scope.saveToLocalStorage();

        } else {
            var url = `${host}/sanpham/${masp}`;
            $http.get(url).then(resp => {
                resp.data.qty = 1;
                $scope.cartItems.push(resp.data);
                $scope.saveToLocalStorage();

            })
        }

        swal("Thành công", "Thêm sản phẩm vào giỏ hàng thành công!", "success")


    }
    // Tính tổng tiền của các sản phẩm trong localStorage

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
    $scope.saveToLocalStorage = function () {
        var json = JSON.stringify(angular.copy($scope.cartItems));
        localStorage.setItem("cart", json);
    }
    $scope.loadLocalStorage = function () {
        var json = localStorage.getItem("cart");
        $scope.cartItems = json ? JSON.parse(json) : [];

    }

    $scope.getcount = function () { // tính tổng số lượng các mặt hàng trong giỏ
        return $scope.cartItems
            .map(cart => cart.qty)
            .reduce((total, qty) => total += qty, 0);

    }

    $scope.loadLocalStorage();

    $scope.amt_of = function (cart) { // tính thành tiền của 1 sản phẩm
        return cart.giasp * cart.qty;
    }
    $scope.totalAmount = function () {
        return $scope.cartItems
            .map(cart => this.amt_of(cart))
            .reduce((total, amt) => total += amt, 0);
    };

    $scope.sortOrder = true;
    // Hàm sắp xếp sản phẩm theo giá
    $scope.sortByPrice = function () {
        $scope.sortOrder = !$scope.sortOrder;
    };
    //hàm tim kiếm sản phẩm 
    $scope.timKiemSanPham = function () {
        // Tạm dừng tải danh sách sản phẩm (nếu có)
        $scope.displayedProducts = [];
        // Reset trang hiện tại về 1
        $scope.currentPage = 1;
        // Lấy danh sách tất cả sản phẩm
        var url = `${host}/sanpham`;
        $http.get(url).then(resp => {
            $scope.sanphams = resp.data;
            $scope.totalProducts = $scope.sanphams.length;
            // Lọc danh sách sản phẩm dựa trên từ khóa tìm kiếm
            $scope.displayedProducts = $scope.sanphams.filter(sp =>
                sp.tenSanPham.toLowerCase().indexOf($scope.keyword.toLowerCase()) !== -1
            ).slice(0, $scope.productsPerPage);
            $scope.showSeeMoreButton = $scope.totalProducts > $scope.productsPerPage;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };
    $scope.login = function () {
        sessionStorage.setItem('redirectFrom', $location.absUrl());

        // Chuyển hướng qua trang đăng nhập
        window.location.href = 'http://localhost:8080/auth/login/form';
    }
    $scope.getAccount = function () {

        // Replace 'YOUR_GET_TOURS_API_URL' with the actual API endpoint to fetch tour products
        var url = 'http://localhost:8080/rest/account';


        $http.get(url).then(resp => {
            $scope.account = resp.data;
            console.log("account", resp)
            if ($scope.account != null) {
                $scope.isLoggedIn = true;
                var redirectFrom = sessionStorage.getItem('redirectFrom');

                if (redirectFrom) {
                    sessionStorage.removeItem('redirectFrom'); // Xóa đường dẫn đã lưu trong sessionStorage
                    window.location.href = redirectFrom; // Chuyển hướng trở lại trang trước đó
                }
            } else {
                $scope.isLoggedIn = false;
            }

        }).catch(error => {
            console.log("Error", error)
        });
    };

    $scope.getAccount();
    $scope.clear = function () {

        swal({
            title: "Cảnh báo",
            text: "Bạn có chắc chắn muốn xóa sản phẩm này?",
            buttons: ["Hủy bỏ", "Đồng ý"],
            icon: "warning",

        }).then((willDelete) => {

            if (willDelete) {
                $scope.cartItems = [];
                $scope.saveToLocalStorage();
                swal("Thành công", "Tất cả sản phẩm đã được xóa!", "success")
                $scope.$apply();
            }
        })



    }




})




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
        return cart.giasp * cart.qty;
    }
    $scope.totalAmount = function () {
        return $scope.cartItems
            .map(cart => this.amt_of(cart))
            .reduce((total, amt) => total += amt, 0);
    };
    $scope.orderDetails2 = function () {
        return $scope.cartItems.map(cart => {
            return {
                sanpham: { masp: cart.masp },
                giasp: cart.giasp,
                soluong: cart.qty,
            };
        });
    };
    $scope.getAccount = function () {

        // Replace 'YOUR_GET_TOURS_API_URL' with the actual API endpoint to fetch tour products
        var url = 'http://localhost:8080/rest/account';


        $http.get(url).then(resp => {
            $scope.account = resp.data;
            console.log("account", resp)
            if ($scope.account != null) {
                $scope.isLoggedIn = true;
            } else {
                $scope.isLoggedIn = false;
            }

        }).catch(error => {
            console.log("Error", error)
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
    $scope.getAccount();

    $scope.loadLocalStorage();

    $scope.order = {
        ngaymua: new Date(),
        khachhang: {},
        tennguoinhan: "", // Thêm trường tên người nhận
        diachinn: "",
        dienthoainn: "",
        trangthaihd: 1,
        tongtien: $scope.totalAmount(),
        ghichu: "Trống", // Thêm trường số điện thoại người nhận
        get hoadonct() {
            return $scope.cartItems.map(cart => {
                return {
                    sanpham: { masp: cart.masp },
                    giasp: cart.giasp,
                    soluong: cart.qty,
                };
            });
        },
        purchase() {
            var order = angular.copy(this);
            var url = "http://localhost:8080/rest/hoadon";
            var khachHang = {
                usernamekh: $scope.account.usernamekh,

            };

            order.tennguoinhan = $scope.account.hotenkh;
            order.khachhang = khachHang,
                order.dienthoainn = $scope.account.dienthoai;
            order.diachinn = $scope.dnct + ',' + diachinn1;
            order.ghichu = $scope.ghichu;



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



app.filter('emailProtection', function () {
    return function (email) {
        if (!email) return '';
        var result = '';
        for (var i = 0; i < email.length; i++) {
            result += '&#' + email.charCodeAt(i) + ';';
        }
        return result;
    };
});
