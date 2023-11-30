var app = angular.module('my-app', []);

app.controller('address-ctrl', function($scope, $http) {
	$scope.provinces = [];

	$http({
		method: 'GET',
		url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/province',
		headers: {
			'Token': 'e052899a-8f52-11ee-b394-8ac29577e80e'
		}
	}).then(function successCallback(response) {
		$scope.province = response.data.data;
		console.log("đây nè: ", $scope.province);
	})

	$scope.getDisplayValue = function(value) {
		// Kiểm tra xem giá trị có chứa "string:" không và loại bỏ nếu có
		if (value && value.indexOf('string:') === 0) {
			return value.replace('string:', '');
		}
		return value;
	};

	$scope.saveSelectedValue = function(selectedValue) {
		// Kiểm tra xem selectedValue có tồn tại và là chuỗi không
		if (selectedValue && typeof selectedValue === 'string') {
			// Xử lý giá trị trước khi lưu
			var processedValue = selectedValue.replace('string:', '');

			// Lưu giá trị đã xử lý vào cơ sở dữ liệu hoặc thực hiện các bước khác cần thiết
			// processedValue sẽ là giá trị đã xử lý, không chứa "string:"
			console.log('Giá trị đã xử lý:', processedValue);

			// Tiếp tục thực hiện các bước lưu giá trị vào cơ sở dữ liệu
			// ...
		} else {
			console.error('Giá trị không hợp lệ:', selectedValue);
		}
	};


});