var app = angular.module('my-app');

app.controller('category-controller', function ($scope, $http, $window) {
    $scope.categoris = [];
    $scope.brands = [];
    $scope.products = [];
    $scope.newCate = {};

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
    $scope.delete = function (cate) {
        // Xác nhận người dùng có muốn xóa danh mục này không
        if (confirm("Bạn có chắc chắn muốn xóa loại này?")) {
            // Gọi API để cập nhật trạng thái active của danh mục
            $http.put(`/delete/${cate.categoryID}`, {})
                .then(function (response) {
                    // Kiểm tra xem $scope.categories đã được định nghĩa và có giá trị không
                    if ($scope.categories && $scope.categories.length > 0) {
                        var index = $scope.categories.findIndex(c => c.categoryID === cate.categoryID);
                        if (index !== -1) {
                            $scope.categories.splice(index, 1); // Xóa danh mục khỏi danh sách
                            swal("Thành công", "Xóa loại thành công!", "success");
                        }
                    }
                    // Cập nhật trạng thái active sau khi API thành công
                    cate.active = false;
                })
                .catch(function (error) {
                    // Xử lý lỗi nếu có
                    swal("Thất bại", "Đã có lỗi xảy ra!", "error");
                    console.error(error);
                });
        }
    };




    $scope.edit = function (categoris) {
        $scope.newCate = angular.copy(categoris);
    }

});
