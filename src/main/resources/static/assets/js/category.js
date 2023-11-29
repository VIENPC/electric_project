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
    
     // Hàm lấy thông tin người dùng từ API
    $scope.getUserInfo = function () {
        $http.get('/api/account/login')  // Thay thế bằng endpoint API thích hợp của bạn
            .then(function (response) {
                // Xử lý kết quả trả về từ API
                var userInfo = response.data;

                // Lưu thông tin người dùng vào biến $scope
                $scope.loggedInUser = userInfo;

                // Hiển thị thông báo hoặc thực hiện các công việc khác dựa trên thông tin người dùng
                console.log('User Info:', userInfo);
            })
            .catch(function (error) {
                console.error('Error fetching user info:', error);
            });
    };

    // Gọi hàm để lấy thông tin người dùng khi trang được tải
    $scope.getUserInfo();


});

