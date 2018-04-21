/**
 * Created by fire on 28/03/18.
 */
(function () {
    'use strict';

    var appCliente = angular.module("appCliente");

    appCliente.factory("ClienteService", function ( $http, $q, Utils) {

        return{
            getCliente: function(dataFilter){
                console.log(Utils.buildQuery(dataFilter));
                return $q(function (resolve,reject) {
                    $http({
                        method: 'GET',
                        url: 'http://18.219.249.32:8080/cliente/getAllBy'+Utils.buildQuery(dataFilter)
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