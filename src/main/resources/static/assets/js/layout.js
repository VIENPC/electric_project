
var app = angular.module("myapp", ["ngRoute", "ngCookies"]);


app.run(function ($rootScope) {
    $rootScope.$on('$routeChangeStart', function () {
        $rootScope.loading = true;
    });
    $rootScope.$on('$routeChangeSuccess', function () {
        $rootScope.loading = false;
    });
    $rootScope.$on('$routeChangeError', function () {
        $rootScope.loading = false;
        alert("Lỗi");
    });
});

let host = "http://localhost:8080/rest";


app.filter('emailProtection', function () {
    return function (email) {
        if (!email) return '';
        var result = '';
        for (var i = 0; i < email.length; i++) {
            result += '&#' + email.charCodeAt(i) + ';';
        }
        return result;
    };

});

