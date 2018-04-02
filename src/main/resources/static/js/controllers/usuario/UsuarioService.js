/**
 * Created by fire on 28/03/18.
 */
(function () {
    'use strict';

    var appUsuario = angular.module("appUsuario");

    appUsuario.factory("AnostraService", function ( $http, $q, Utils) {

        return{
            getUsuario: function(dataFilter){
                console.log(Utils.buildQuery(dataFilter));
                return $q(function (resolve,reject) {
                    $http({
                        method: 'GET',
                        url: 'http://localhost:8091/usuario/getAll'+Utils.buildQuery(dataFilter)
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