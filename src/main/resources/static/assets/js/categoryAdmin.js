var app = angular.module('my-app');

app.controller('categoryAdmin-controller', function ($scope, $http, $window) {
  $scope.categoris = [];
  $scope.newCate = {};
  $scope.form = {};
  $scope.pageNumbers = []; // Các số trang
  $scope.currentPage = 1;  // Trang hiện tại
  $scope.pageSize = 8;


  $http.get('/rest/categoryAdmin')
    .then(function (response) {
      $scope.categoris = response.data;
      console.log($scope.categoris);
      $scope.calculatePageNumbersCate(); // Tính toán số trang sau khi tải dữ liệu
      $scope.updateDisplayedCategories();
    });



  // Hàm để cập nhật danh sách sản phẩm được hiển thị trên trang hiện tại
  $scope.updateDisplayedCategories = function () {
    var startIndex = ($scope.currentPage - 1) * $scope.pageSize;
    var endIndex = startIndex + $scope.pageSize;
    $scope.categoris = $scope.categoris.slice(startIndex, endIndex);
  };
  // Hàm để tính toán danh sách các số trang
  $scope.calculatePageNumbersCate = function () {
    var totalPages = Math.ceil($scope.categoris.length / $scope.pageSize);
    $scope.pageNumbers = [];
    for (var i = 1; i <= totalPages; i++) {
      $scope.pageNumbers.push(i);
    }
  }
  // Hàm để chuyển đến trang khác
  $scope.goToPage = function (page) {
    $scope.currentPage = page;
    $scope.updateDisplayedCategories();
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

  // Hàm xử lý sự kiện chuyển về Tab 2
  $scope.switchToTab2 = function () {
    $('#myTabs a[href="#tab2"]').tab('show');
  };
  // Hàm xử lý sự kiện chuyển về Tab 1
  $scope.switchToTab1 = function () {
    $('#myTabs a[href="#tab1"]').tab('show');

  };

  $scope.edit = function (categoris) {
    $scope.newCate = angular.copy(categoris);
  }
});