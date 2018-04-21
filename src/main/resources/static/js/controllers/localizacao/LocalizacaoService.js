(function () {
    'use strict';

    var appLocalizacao = angular.module("appLocalizacao");

    appLocalizacao.factory("LocalizacaoService", function ( $http, $q, Utils) {

        return{
            getLocalizacao: function(dataFilter){
                console.log(Utils.buildQuery(dataFilter));
                return $q(function (resolve,reject) {
                    $http({
                        method: 'GET',
                        url: 'http://18.219.249.32:8080/localizacao/getAllBy'+Utils.buildQuery(dataFilter)
                    }).then(function successCallback(response) {
                        resolve (response.data)
                    }, function errorCallback(response) {
                        reject (response.status)
                    });
                })
            }
        }
    })
})();