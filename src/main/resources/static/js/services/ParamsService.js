
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

                 resolve({accessKey:'AKIAIC234XTJBTLUA6QA',secretKey:'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',bucket:'bordamax'})

            })
        }
    }
});
})();
