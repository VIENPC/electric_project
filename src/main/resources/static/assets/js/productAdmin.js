var app = angular.module('my-app');

app.controller('productAdmin-controller', function ($scope, $http, $window, $sce) {
  $scope.products = {};
  $scope.products = [];
  $scope.newProduct = {};
  $scope.form = {};
  $scope.pageNumbers = []; // Các số trang
  $scope.currentPage = 1;  // Trang hiện tại
  $scope.pageSize = 6;
  $scope.editingMode = false; // Ban đầu, không ở chế độ chỉnh sửa
  $scope.addingMode = true; // Ban đầu, không ở chế độ chỉnh sửa

  $scope.reset = function () {
    $scope.newProduct = {
      quantity: null,
      productName: '',
      configuration: '',
      description: '',
      image: null

    };
    $scope.form.$setPristine();
    $scope.form.$setUntouched();
  };
  $scope.updateStatus = function () {
    if ($scope.newProduct.quantity <= 0) {
      $scope.newProduct.active = false;
    } else {
      $scope.newProduct.active = true;
    }
  };
  $http.get('/rest/productAdmin')
    .then(function (response) {
      $scope.products = response.data;
      $scope.allProducts = response.data;
      $scope.calculatePageNumbers(); // Tính toán số trang sau khi tải dữ liệu
      $scope.updateDisplayedProducts();
    });

  // Hàm để cập nhật danh sách sản phẩm được hiển thị trên trang hiện tại
  $scope.updateDisplayedProducts = function () {
    var startIndex = ($scope.currentPage - 1) * $scope.pageSize;
    var endIndex = startIndex + $scope.pageSize;
    $scope.products = $scope.allProducts.slice(startIndex, endIndex);
  };
  // Hàm để tính toán danh sách các số trang
  $scope.calculatePageNumbers = function () {
    var totalPages = Math.ceil($scope.allProducts.length / $scope.pageSize);
    $scope.pageNumbers = [];
    for (var i = 1; i <= totalPages; i++) {
      $scope.pageNumbers.push(i);
    }
  }

  // Hàm để chuyển đến trang khác
  $scope.goToPage = function (page) {
    $scope.currentPage = page;
    $scope.updateDisplayedProducts();
  };

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
    $scope.newProduct.configuration = products.configuration;
    $scope.newProduct.description = products.description;
    $scope.editingMode = true;
    $scope.addingMode = false; // Ban đầu, không ở chế độ chỉnh sửa
    // Cập nhật giá trị CKEditor
    CKEDITOR.instances.configuration.setData($scope.newProduct.configuration);
    CKEDITOR.instances.description.setData($scope.newProduct.description);
    $scope.switchToTab2();
  }


  // Hàm xử lý sự kiện chuyển về Tab 2
  $scope.switchToTab2 = function () {
    $('#myTabs a[href="#tab2"]').tab('show');
  };
  // Hàm xử lý sự kiện chuyển về Tab 1
  $scope.switchToTab1 = function () {
    $('#myTabs a[href="#tab1"]').tab('show');

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
        $scope.reset();
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
});