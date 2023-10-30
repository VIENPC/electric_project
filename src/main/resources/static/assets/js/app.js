var app = angular.module('my-app', []); // Đăng ký module

app.filter('unsafeHtml', ['$sce', function ($sce) {
    return function (val) {
        return $sce.trustAsHtml(val);
    };
}]);

