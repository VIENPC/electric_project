document.getElementById('loai1').addEventListener('change', function() {
	var selectedValue = this.value;
	var loai2Select = document.getElementById('loai2');
	loai2Select.innerHTML = ''; // Xóa tất cả các tùy chọn cũ trong Select 2
	var options = Array.from(document.querySelectorAll('#loai1 option'));
	options.forEach(function(option) {
		if (option.value !== selectedValue) {
			var clonedOption = option.cloneNode(true);
			loai2Select.appendChild(clonedOption);
		}
	});
});
document.getElementById('hang1').addEventListener('change', function() {
	var selectedValue = this.value;
	var hang2Select = document.getElementById('hang2');
	hang2Select.innerHTML = ''; // Xóa tất cả các tùy chọn cũ trong Select 2
	var options = Array.from(document.querySelectorAll('#hang1 option'));
	options.forEach(function(option) {
		if (option.value !== selectedValue) {
			var clonedOption = option.cloneNode(true);
			hang2Select.appendChild(clonedOption);
		}
	});
});


$(document).ready(function () {
	$('#sampleTable').DataTable({
		"order": []  // Đặt thứ tự sắp xếp ban đầu là mảng rỗng
	});
});

$(document).ready(function () {
	$('#sampleTable1').DataTable({
		"order": []  // Đặt thứ tự sắp xếp ban đầu là mảng rỗng
	});
});

$(document).ready(function () {
	$('#sampleTablebaocaochung1').DataTable({
		"order": []  // Đặt thứ tự sắp xếp ban đầu là mảng rỗng
	});
});

$(document).ready(function () {
	$('#sampleTable2').DataTable({
		"order": []  // Đặt thứ tự sắp xếp ban đầu là mảng rỗng
	});
});
$(document).ready(function () {
	$('#sampleTable3').DataTable({
		"order": []  // Đặt thứ tự sắp xếp ban đầu là mảng rỗng
	});
});
$(document).ready(function () {
	// Xử lý sự kiện khi nhấn vào button
	$('#sidebarCollapse').click(function () {
		// Thêm hoặc xóa lớp CSS 'active' của thẻ nav
		$('#sidebar').toggleClass('active');
	});

	$('#checkboxall').click(function () {
		if ($(this).is(":checked")) {
			$('.chkboxId').prop('checked', true);
		} else {
			$('.chkboxId').prop('checked', false);
		}
	});
});

function submitFormLoai() {
	document.getElementById("formloai").submit();
}
// xử lí phần thống kê theo hãng
function submitForm() {
	document.getElementById("formhang").submit();
}
function submitFormSPBC(selectElement) {
	var selectedValue = selectElement.value;
	// Kiểm tra giá trị đã chọn và thực hiện hành động tương ứng
	if (selectedValue) {
		document.getElementById('formthang').submit();
	}
}
// $('#all').click(
// 	function(e) {
// 		$('#sampleTable tbody :checkbox').prop('checked',
// 			$(this).is(':checked'));
// 		e.stopImmediatePropagation();
// 	});
//Thời Gian

function removeSuccessParamFromURL() {
	if (window.history && window.history.replaceState) {
		var url = window.location.href;
		var urlParams = new URLSearchParams(window.location.search);
		urlParams.delete('success');
		var newURL = url.split('?')[0] + urlParams.toString();
		window.history.replaceState({}, '', newURL);
	}
}
function removeFailParamFromURL() {
	if (window.history && window.history.replaceState) {
		var url = window.location.href;
		var urlParams = new URLSearchParams(window.location.search);
		urlParams.delete('fail');
		var newURL = url.split('?')[0] + urlParams.toString();
		window.history.replaceState({}, '', newURL);
	}
}
// Kiểm tra query parameter và hiển thị thông báo thành công
window.onload = function () {
	var urlParams = new URLSearchParams(window.location.search);
	var successParam = urlParams.get('success');
	var failParam = urlParams.get('fail');
	//thành công 
	if (successParam == "add") {
		showSuccessMessage("Thêm sản phẩm thành công");
		removeSuccessParamFromURL();
	}

	if (successParam === 'delete') {
		showSuccessMessage("Xoá sản phẩm thành công");
		removeSuccessParamFromURL();
	}
	if (successParam === 'updatesp') {
		showSuccessMessage("Cập nhật thành công");
		removeSuccessParamFromURL();
	}

	//thất bại
	if (failParam == 'delete') {
		showFailMessage("Xoá sản phẩm thất bại");
		removeFailParamFromURL();
	}
	if (failParam == 'updatesp') {
		showFailMessage("Cập nhật sản phẩm thất bại");
		removeFailParamFromURL();
	}
}
function showFailMessage(message) {
	swal("Thông báo", message, "error");
}
function showSuccessMessage(message) {
	swal("Thông báo", message, "success");
}
// xóa nhiều sản phẩm cùng lúc
// $('.xoansp').click(function(){
// 	swal({
// 		title: "Cảnh báo",
// 		text: "Bạn có chắc chắn muốn xóa sản phẩm này?",
// 		buttons: ["Hủy bỏ", "Đồng ý"],
// 	}).then((willDelete) => {
// 		window.location.href = `/qlsanpham/delnhieusp`;
// 	})
// });  
function edithd(button){

	var row = button.closest("tr");
	var mahd = row.querySelector("td:nth-child(2)").innerText;
	var tinhtrang = row.querySelector("td:nth-child(10)").innerText;
	document.getElementById("mahd").value = mahd;
	document.getElementById("tinhtrang").value = tinhtrang;
	$("#edittt").modal("show");
}


$('.unclock').click(function () {
	const user = $(this).data("user");
	swal({
		title: "Cảnh báo",
		text: "Bạn có chắc chắn muốn chỉnh sửa trạng thái này?",
		buttons: ["Hủy bỏ", "Đồng ý"],
	}).then((willEdit) => {
		if (willEdit) {

			if (user != null) {

				window.location.href = `/admin/qlkhachhang/unclock/${user}`;
			}

		}
	})
})


$(document).on('click', '.trash', function () {
	const itemId = $(this).data("masp");

	swal({
		title: "Cảnh báo",
		text: "Bạn có chắc chắn muốn xóa sản phẩm này?",
		buttons: ["Hủy bỏ", "Đồng ý"],
		icon: "warning"
	}).then((willDelete) => {
		if (willDelete) {
			// Chuyển hướng tới endpoint xử lý xóa sản phẩm khi người dùng nhấn "Đồng ý"
			// Thay 123 bằng id của sản phẩm cần xóa
			window.location.href = `/admin/qlsanpham/deletesp/${itemId}`;

		}

	});
});

$('.export').click(function () {
	var table2excel = new Table2Excel();
	table2excel.export(document.querySelectorAll("table.table"));
});
$('.pdf-file').click(function () {

	var elment = document.getElementById('sampleTable');
	var opt = {
		margin: 0.5,
		filename: 'myfilepdf.pdf',
		image: { type: 'jpeg', quality: 0.98 },
		html2canvas: { scale: 2 },
		jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
	};
	html2pdf(elment, opt);
});

var myApp = new function () {
	this.printTable = function () {
		var tab = document.getElementById('sampleTable');
		var win = window.open('', '', 'height=700,width=700');
		win.document.write(tab.outerHTML);
		win.document.close();
		win.print();
	}

}

//xử lí modal của edit sản phẩm
var masp2;
function checkmasp() {
	// alert(masp2);
	var editLink = document.getElementById("editLink");
	editLink.href = "/admin/addsanpham/" + masp2;

}

function editProduct(button) {

	// Trích xuất thông tin sản phẩm từ hàng đã chọn
	var row = button.closest("tr");
	var masp = row.querySelector("td:nth-child(2)").innerText;
	var tensp = row.querySelector("td:nth-child(3)").innerText;
	var soluong = row.querySelector("td:nth-child(5)").innerText;
	var tinhtrang = row.querySelector("td:nth-child(6)").innerText;
	var giasp = row.querySelector("td:nth-child(7)").innerText;



	// Đổ thông tin sản phẩm vào modal
	document.getElementById("masp").value = masp;
	masp2 = masp;

	document.getElementById("tensp").value = tensp;
	document.getElementById("soluong").value = soluong;
	document.getElementById("tinhtrang").value = tinhtrang;
	document.getElementById("giasp").value = giasp;
	var categories1 = [];
	var data4 = [];
	var table4 = document.getElementById("bang4");
	// Lấy tất cả các hàng trong bảng
	var rows = table4.querySelectorAll('table tr');

	// Lặp qua từng hàng trong bảng
	rows.forEach(function (row) {
		var columns = row.querySelectorAll('td');
		if (columns.length === 2) {
			// Lấy dữ liệu từ cột đầu tiên và thêm vào mảng categories
			categories1.push(columns[0].textContent);

			// Lấy dữ liệu từ cột thứ hai và thêm vào mảng data
			data4.push(parseFloat(columns[1].textContent)); // Chuyển đổi sang kiểu số nếu cần
		}
		for (var i = 0; i < data4.length; i++) {
			data4[i].name = categories1[i]; // Thêm tên tháng
			data4[i].symbol = 'Mmm'; // Thêm kí tự, thay 'A' bằng kí tự bạn muốn
		}
	});

	//Đây là biểu đồ
	const chart1 = Highcharts.chart('container1', {
		title: {
			text: 'THỐNG KÊ DOANH THU THEO HÃNG',
			align: 'center'
		},
		colors: [
			'#4caefe',
			'#3fbdf3',
			'#35c3e8',
			'#2bc9dc',
			'#20cfe1',
			'#16d4e6',
			'#0dd9db',
			'#03dfd0',
			'#00e4c5',
			'#00e9ba',
			'#00eeaf',
			'#23e274'
		],
		xAxis: [{
			categories: categories1.map(function (category1) {
				return 'Tháng ' + category1;
			}),
			// crosshair: true
		}],
		series: [{
			type: 'column',
			name: 'Doanh thu',
			borderRadius: 5,
			colorByPoint: true,
			data: data4,
			showInLegend: false
		}]
	});

	document.getElementById('plain1').addEventListener('click', () => {
		chart1.update({
			chart: {
				inverted: false,
				polar: false
			},

		});
	});

	document.getElementById('inverted1').addEventListener('click', () => {
		chart1.update({
			chart: {
				inverted: true,
				polar: false
			},
		});
	});
	var table1 = document.getElementById("bang1");
	var rows = table1.querySelectorAll('table tr');
	// Hiển thị modal
	$("#ModalUP").modal("show");
}

var categories = [];
var categories1 = [];
var data4 = [];
var table4 = document.getElementById("bang4");
// Lấy tất cả các hàng trong bảng
var rows = table4.querySelectorAll('table tr');

// Lặp qua từng hàng trong bảng
rows.forEach(function (row) {
	var columns = row.querySelectorAll('td');
	if (columns.length === 2) {
		// Lấy dữ liệu từ cột đầu tiên và thêm vào mảng categories
		categories1.push(columns[0].textContent);

		// Lấy dữ liệu từ cột thứ hai và thêm vào mảng data
		data4.push(parseFloat(columns[1].textContent)); // Chuyển đổi sang kiểu số nếu cần
	}
	for (var i = 0; i < data4.length; i++) {
		data4[i].name = categories1[i]; // Thêm tên tháng
		data4[i].symbol = 'Mmm'; // Thêm kí tự, thay 'A' bằng kí tự bạn muốn
	}
});

//Đây là biểu đồ
const chart1 = Highcharts.chart('container1', {
	title: {
		text: 'THỐNG KÊ DOANH THU THEO HÃNG VÀ LOẠI',
		align: 'center'
	},
	colors: [
		'#4caefe',
		'#3fbdf3',
		'#35c3e8',
		'#2bc9dc',
		'#20cfe1',
		'#16d4e6',
		'#0dd9db',
		'#03dfd0',
		'#00e4c5',
		'#00e9ba',
		'#00eeaf',
		'#23e274'
	],
	xAxis: [{
		categories: categories1.map(function (category1) {
			return 'Tháng ' + category1;
		}),
		// crosshair: true
	}],
	series: [{
		type: 'column',
		name: 'Doanh thu',
		borderRadius: 5,
		colorByPoint: true,
		data: data4,
		showInLegend: false
	}]
});

document.getElementById('plain1').addEventListener('click', () => {
	chart1.update({
		chart: {
			inverted: false,
			polar: false
		},

	});
});

document.getElementById('inverted1').addEventListener('click', () => {
	chart1.update({
		chart: {
			inverted: true,
			polar: false
		},
	});
});
var data = [];
var table1 = document.getElementById("bang1");
// Lấy tất cả các hàng trong bảng
var rows = table1.querySelectorAll('table tr');

// Lặp qua từng hàng trong bảng
rows.forEach(function (row) {
	var columns = row.querySelectorAll('td');
	if (columns.length === 2) {
		// Lấy dữ liệu từ cột đầu tiên và thêm vào mảng categories
		categories.push(columns[0].textContent);

		// Lấy dữ liệu từ cột thứ hai và thêm vào mảng data
		data.push(parseFloat(columns[1].textContent)); // Chuyển đổi sang kiểu số nếu cần
	}
});
const chart = Highcharts.chart('container', {
	title: {
		text: 'THỐNG KÊ DOANH THU',
		align: 'center'
	},
	colors: [
		'#4caefe',
		'#3fbdf3',
		'#35c3e8',
		'#2bc9dc',
		'#20cfe1',
		'#16d4e6',
		'#0dd9db',
		'#03dfd0',
		'#00e4c5',
		'#00e9ba',
		'#00eeaf',
		'#23e274'
	],
	xAxis: [{
		categories: categories.map(function (category) {
			return 'Tháng ' + category;
		}),
		// crosshair: true
	}],
	series: [{
		type: 'column',
		name: 'Doanh thu',
		borderRadius: 5,
		colorByPoint: true,
		data: data,
		showInLegend: false
	}]
});

document.getElementById('plain').addEventListener('click', () => {
	chart.update({
		chart: {
			inverted: false,
			polar: false
		},

	});
});

document.getElementById('inverted').addEventListener('click', () => {
	chart.update({
		chart: {
			inverted: true,
			polar: false
		},
	});
});

function editCategory(button) {

	// Trích xuất thông tin sản phẩm từ hàng đã chọn
	var row = button.closest("tr");
	var categoryID = row.querySelector("td:nth-child(2)").innerText;
	var categoryName = row.querySelector("td:nth-child(3)").innerText;
	var active = row.querySelector("td:nth-child(4)").innerText;

	// Đổ thông tin sản phẩm vào modal
	document.getElementById("categoryID").value = categoryID;
	document.getElementById("categoryName").value = categoryName;
	document.getElementById("active").value = active;


	// Hiển thị modal
	$("#ModalUP2").modal("show");
}
$(document).ready(function () {
	// Bắt sự kiện khi bấm vào nút "Chuyển đến Tab 2" trong Tab 1
	$('#switchToTab2').click(function () {
		$('#myTabs a[href="#tab2"]').tab('show');
	});

	// Bắt sự kiện khi bấm vào nút "Chuyển đến Tab 1" trong Tab 2
	$('#switchToTab1').click(function () {
		$('#myTabs a[href="#tab1"]').tab('show');
	});
});
function submitFormdm() {

	document.getElementById("formdm").submit();
}
//Biểu đồ 2 

var categories = [];
var data2 = [];
var table2 = document.getElementById("bang2");
// Lấy tất cả các hàng trong bảng
var rows = table2.querySelectorAll('table tr');
// Lặp qua từng hàng trong bảng

rows.forEach(function (row) {
	var columns = row.querySelectorAll('td');
	if (columns.length === 2) {
		// Lấy dữ liệu từ cột đầu tiên và thêm vào mảng categories
		categories.push(columns[0].textContent);

		// Lấy dữ liệu từ cột thứ hai và thêm vào mảng data
		data2.push(parseFloat(columns[1].textContent)); // Chuyển đổi sang kiểu số nếu cần
	}
});
var data3 = [];
var table3 = document.getElementById("bang3");
// Lấy tất cả các hàng trong bảng
var rows = table3.querySelectorAll('table tr');
// Lặp qua từng hàng trong bảng

rows.forEach(function (row) {
	var columns = row.querySelectorAll('td');
	if (columns.length === 2) {
		// Lấy dữ liệu từ cột đầu tiên và thêm vào mảng categories
		categories.push(columns[0].textContent);

		// Lấy dữ liệu từ cột thứ hai và thêm vào mảng data
		data3.push(parseFloat(columns[1].textContent)); // Chuyển đổi sang kiểu số nếu cần
	}
});
Highcharts.chart('container2', {
	chart: {
		zoomType: 'xy'
	},
	title: {
		text: 'Thống kê số khách hàng đăng kí và đơn hàng theo tháng',
		align: 'center'
	},
	xAxis: [{
		categories: categories.map(function (category) {
			return 'Tháng ' + category;
		}),
		crosshair: true
	}],
	yAxis: [{ // Primary yAxis
		labels: {
			forma: function () {
				// this.value chứa giá trị dữ liệu của bạn
				// Thay thế {data} bằng giá trị dữ liệu của bạn
				return this.value + 'người'; // Thay đổi '°C' bằng đơn vị hoặc chuỗi bạn muốn hiển thị
			},
			style: {
				color: Highcharts.getOptions().colors[1]
			}
		},
		title: {
			text: '',
			style: {
				color: Highcharts.getOptions().colors[1]
			}
		}
	}, { // Secondary yAxis
		title: {
			text: '',
			style: {
				color: Highcharts.getOptions().colors[0]
			}
		},

		labels: {
			formatter: function () {
				// this.value chứa giá trị dữ liệu của bạn
				// Thay thế {data} bằng giá trị dữ liệu của bạn
				return Math.round(this.value); // Thay đổi '°C' bằng đơn vị hoặc chuỗi bạn muốn hiển thị
			},
			style: {
				color: Highcharts.getOptions().colors[0]
			}
		},
		opposite: true
	}],
	tooltip: {
		shared: true
	},
	legend: {
		align: 'left',
		x: 80,
		verticalAlign: 'top',
		y: 60,
		floating: true,
		backgroundColor:
			Highcharts.defaultOptions.legend.backgroundColor || // theme
			'rgba(255,255,255,0.25)'
	},
	series: [{
		name: 'Số lượng đơn hàng',
		type: 'column',
		data: data3,
		tooltip: {
			valueSuffix: ' đơn hàng'
		}

	}, {
		name: 'Số người đăng kí',
		type: 'spline',
		data: data2,
		tooltip: {
			valueSuffix: ' người đăng kí'
		}
	}]
});






