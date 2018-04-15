
(function () {
    'use strict';

    var appBordamax = angular.module("appBordamax");

    appBordamax.factory('ParamsService', function ($q, $http) {
    return{
        getAwsParams: function () {
            return $q(function (resolve, reject) {
                $http.get(serverUrl+restAwsParams).then(function (res) {
                    resolve(res.data);
                },function (err) {
                    console.log('ERRO >>>> '+JSON.stringify(err));
                    reject(err);
                })
            })
        },getAwsParamsFixed: function () {
            return $q(function (resolve, reject) {
                resolve({accessKey:'AKIAJFS22SZT32VIQ5SA',secretKey:'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',bucket:'memoriasviagens'})
            })
        }
    }
});
})();
