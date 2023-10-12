var app = angular.module('my-app');

app.controller('supplierAdmin-controller', function ($scope, $http, $window) {
  $scope.suppliers = [];
  $scope.newSup = {};
  $scope.form = {};
  $scope.editingMode = false; // Ban đầu, không ở chế độ chỉnh sửa
  $scope.addingMode = true; // Ban đầu, không ở chế độ chỉnh sửa

  $http.get('/rest/supplier')
    .then(function (response) {
      $scope.suppliers = response.data;
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
  $scope.editSupplier = function (suppliers) {
    $scope.newSup = angular.copy(suppliers);
    $scope.editingMode = true; // Ban đầu, không ở chế độ chỉnh sửa
    $scope.addingMode = false; // Ban đầu, không ở chế độ chỉnh sửa
    $scope.switchToTab1();
  }
  // Hàm xử lý sự kiện chuyển về Tab 2
  $scope.switchToTab2 = function () {
    $('#myTabs a[href="#tab2"]').tab('show');
  };
  // Hàm xử lý sự kiện chuyển về Tab 1
  $scope.switchToTab1 = function () {
    $('#myTabs a[href="#tab1"]').tab('show');
  };

  $scope.updateStatus = function () {
    if ($scope.newProduct.quantity <= 0) {
      $scope.newProduct.active = false;
    } else {
      $scope.newProduct.active = true;
    }
  };
});