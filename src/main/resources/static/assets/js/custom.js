$('#sampleTable').DataTable();


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

$(document).on('click', '.settt', function () {
	const mahd = $(this).data("mahd");


	swal({
		title: "Cảnh báo",
		text: "Bạn có chắc chắn muốn chỉnh sửa trạng thái này?",
		buttons: ["Hủy bỏ", "Đồng ý"],
		icon: "warning"
	}).then((willEdit) => {
		if (willEdit) {
			if (mahd != null) {
				window.location.href = `/admin/qldonhang/suatthd/${mahd}`;
			}


		}
	})

});

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


	// Hiển thị modal
	$("#ModalUP").modal("show");
}








