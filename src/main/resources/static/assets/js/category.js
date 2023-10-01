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

    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/uploads/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.newBrand.image = resp.data.name;
        }).catch(error => {
            alert("Loi roi");
            console.log("Error:" + error);
        })
    }

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

    $scope.addCategory = function () {
        $http.post('/admin/create', $scope.newCate)
            .then(function (response) {
                $scope.categoris.push(response.data);;
                $scope.newCate = {};
                swal("Thành công", "Thêm loại thành công!", "success")
                $scope.loadCate();
            });
    };
    $scope.updateCate = function () {
        // Thực hiện kiểm tra form có hợp lệ hay không
        if ($scope.form.$invalid) {
            // Hiển thị thông báo lỗi nếu form không hợp lệ
            swal("Thất bại", "Vui lòng nhập vào để thay đổi!", "error")
            return;
        }

        // Gọi API để cập nhật thông tin item
        $http.put(`/admin/${$scope.newCate.categoryID}`, $scope.newCate)
            .then(function (response) {
                // Tìm và cập nhật thông tin người dùng trong mảng $scope.users
                var index = $scope.categoris.findIndex(cate => cate.categoryID === $scope.newCate.categoryID);
                if (index !== -1) {
                    $scope.categoris[index] = response.data;
                }
                $scope.newCate = {};
                swal("Thành công", "Cập nhật loại thành công!", "success")
            })
            .catch(function (error) {
                // Xử lý lỗi nếu có
                swal("Thất bại", "Cập nhật loại không thành công!", "error")
                console.error(error);
            });
    };
    $scope.deleteCategory = function (category) {
        // Xác nhận người dùng muốn cập nhật trạng thái active về false
        if (confirm("Bạn có chắc chắn muốn xóa loại này?")) {
            category.active = false;

            // Gọi API để cập nhật trạng thái active của người dùng
            $http.put(`/deleteCategory/${category.categoryID}`, category)
                .then(function (response) {
                    // Cập nhật thông tin người dùng trong mảng $scope.users
                    var index = $scope.categoris.findIndex(u => u.categoryID === category.categoryID);
                    if (index !== -1) {
                        $scope.categoris[index] = response.data;
                        swal("Thành công", "Xóa loại thành công!", "success")
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
    $scope.addBrand = function () {
        $http.post('/admin/createBrand', $scope.newBrand)
            .then(function (response) {
                $scope.brands.push(response.data);;
                $scope.newBrand = {};
                swal("Thành công", "Thêm thương hiệu thành công!", "success")
            });
    };

    $scope.updateBrand = function () {
        // Thực hiện kiểm tra form có hợp lệ hay không
        if ($scope.form.$invalid) {
            // Hiển thị thông báo lỗi nếu form không hợp lệ
            swal("Thất bại", "Vui lòng nhập vào để thay đổi!", "error")
            return;
        }

        // Gọi API để cập nhật thông tin item
        $http.put(`/adminUpdateBrand/${$scope.newBrand.brandID}`, $scope.newBrand)
            .then(function (response) {
                // Tìm và cập nhật thông tin người dùng trong mảng $scope.users
                var index = $scope.brands.findIndex(brand => brand.brandID === $scope.newBrand.brandID);
                if (index !== -1) {
                    $scope.brands[index] = response.data;
                }
                $scope.newBrand = {};
                swal("Thành công", "Cập nhật thương hiệu thành công!", "success")
            })
            .catch(function (error) {
                // Xử lý lỗi nếu có
                swal("Thất bại", "Cập nhật không thành công!", "error")
                console.error(error);
            });
    };

    $scope.deleteBrand = function (brand) {
        // Xác nhận người dùng muốn cập nhật trạng thái active về false
        if (confirm("Bạn có chắc chắn muốn xóa thương hiệu này?")) {
            brand.active = false;

            // Gọi API để cập nhật trạng thái active của người dùng
            $http.put(`/deleteBrand/${brand.brandID}`, brand)
                .then(function (response) {
                    // Cập nhật thông tin người dùng trong mảng $scope.users
                    var index = $scope.brands.findIndex(u => u.brandID === brand.brandID);
                    if (index !== -1) {
                        $scope.brands[index] = response.data;
                        swal("Thành công", "Xóa thương hiệu thành công!", "success")
                    }
                })
                .catch(function (error) {
                    // Xử lý lỗi nếu có
                    swal("Thất bại", "Xóa không thành công!", "error")
                    console.error(error);
                    // Phục hồi trạng thái active về true nếu xảy ra lỗi
                    brand.active = true;
                });
        }
    };



    // Hàm xử lý sự kiện chuyển về Tab 1
    $scope.switchToTab1 = function () {
        $('#myTabs a[href="#tab1"]').tab('show');
    };

    $scope.editbrand = function (brands) {
        $scope.newBrand = angular.copy(brands);
        $scope.switchToTab1();
    }

    $scope.edit = function (categoris) {
        $scope.newCate = angular.copy(categoris);
    }

});

