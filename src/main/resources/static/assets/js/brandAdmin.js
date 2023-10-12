var app = angular.module('my-app');

app.controller('brandAdmin-controller', function ($scope, $http, $window) {
  $scope.brands = [];
  $scope.newBrand = {};
  $scope.form = {};
  $scope.editingMode = false; // Ban đầu, không ở chế độ chỉnh sửa
  $scope.addingMode = true; // Ban đầu, không ở chế độ chỉnh sửa


  $http.get('/rest/brandAdmin')
    .then(function (response) {
      $scope.brands = response.data;
    });

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
  // Hàm xử lý sự kiện chuyển về Tab 1
  $scope.switchToTab2 = function () {
    $('#myTabs a[href="#tab2"]').tab('show');
  };

  $scope.editbrand = function (brands) {
    $scope.newBrand = angular.copy(brands);
    $scope.editingMode = true; // Ban đầu, không ở chế độ chỉnh sửa
    $scope.addingMode = false; // Ban đầu, không ở chế độ chỉnh sửa
    $scope.switchToTab1();
  }
});