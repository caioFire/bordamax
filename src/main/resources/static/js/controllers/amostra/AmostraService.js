/**
 * Created by fire on 28/03/18.
 */
(function () {
    'use strict';

    var appAmostra = angular.module("appAmostra");

    appAmostra.factory("AmostraService", function ( $http, $q, Utils) {

        return{
            getAmostra: function(dataFilter){
                console.log(Utils.buildQuery(dataFilter));
                return $q(function (resolve,reject) {
                    $http({
                        method: 'GET',
                        url: 'http://23.235.228.203/amostra/getAllBy'+Utils.buildQuery(dataFilter)
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