const host = "http://localhost:8080/rest";
const app = angular.module("OrderApp", []);
const table = document.getElementById("myTable");
const button = document.getElementById("toggleButton");
const form = document.getElementById("form");
const nam = document.getElementById("nam");
const nu = document.getElementById("nu");

app.controller("OrderCtrl", function($scope, $http,$window) {
	$scope.listusers = [];
	$scope.load_all = function() {
		const url = `${host}/users`;
		$http.get(url).then((resp) => {
			$scope.listusers = resp.data;
			console.log("Sucsess", resp.data);
			$scope.pageNumber = 1;
			$scope.limit = 8;
			$scope.totalPage = getTotalPage($scope.listusers, $scope.limit);


		})
	}
	$scope.edit = {};
	var userID =""
	$scope.showUserDetails = function(user) {
		form2.style.display = "block";
		table.style.display = "none";
		$scope.edit = angular.copy(user);
        userID = user.userID
		if ($scope.edit.gender == true) {
			document.getElementById('nam').checked = true;
		} else {
			document.getElementById('nu').checked = true;
		}

		if ($scope.edit.lockStatus == true) {
			document.getElementById('true').checked = true;
		} else {
			document.getElementById('false').checked = true;
		}
		console.log($scope.edit.role)
		if ($scope.edit.role == "ADMIN") {
			document.getElementById('admin').checked = true;
		} else {
			document.getElementById('user').checked = true;
		}
	};
	var table = document.getElementById("myTable");
		var form2 = document.getElementById("form2");
		var button = document.getElementById("toggleButton");
		var form = document.getElementById("form");
		var historyLink = document.getElementById("historyLink");
        $scope.showHistory = function() {		
			form.style.display = "block";
			form2.style.display = "none"; // Ẩn form2
			table.style.display = "none";
		}
      $scope.toggleTableAndInputs = function() {			
			table.style.display = "table";
			form.style.display = "none";
			form2.style.display = "none"; // Hiển thị form2
		}
	$scope.setLimit = (soSanPham) => {
		$scope.pageNumber = 1;
		$scope.limit = soSanPham;
		$scope.totalPage = getTotalPage($scope.listusers, $scope.limit);
	};
	$scope.setPageNumber = (pageNumber) => {
		$scope.pageNumber = pageNumber;
	};
	const getTotalPage = (arr, soSanPham) => {
		return Math.ceil(arr.length / soSanPham);
	};


	$scope.updateUser = (listusers) => {
		var id=parseInt(userID, 10);
console.log(listusers)
		$http.put("http://localhost:8080/rest/users/" + id, listusers).then(resp => {
			console.log("sucesss", resp.data)
			alert("Sửa thành công!");
			 $window.location.href = 'qlkhachhang';
		})
	};
$scope.deleteUser = function() {
    var id = parseInt(userID, 10);
    $http.delete("http://localhost:8080/rest/users/" + id).then(
        resp => {
            console.log("success", resp.data);
            alert("Xóa thành công!");
            $window.location.href = 'qlkhachhang';
        },
        error => {
            console.error("error", error);

            if (error.status === 500) {
                // Lỗi server - có thể là lỗi khóa ngoại
                alert("Không thể xóa người dùng này vì có ràng buộc khóa ngoại.");
            } else {
                // Xử lý các lỗi khác tùy theo trường hợp
                alert("Đã xảy ra lỗi khi xóa người dùng.");
            }
        }
    );
};







	$scope.load_all();
}

);
app.filter('range', function() {
	return function(input, total) {
		total = parseInt(total);

		for (var i = 0; i < total; i++) {
			input.push(i);
		}

		return input;
	};
}
);