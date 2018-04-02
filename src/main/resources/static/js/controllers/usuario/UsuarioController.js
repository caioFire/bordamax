/**
 * Created by fire on 20/03/18.
 */

(function () {
    'use strict';

    var appUsuario = angular.module("appUsuario");

    appUsuario.controller ("usuarioController", function ($scope, $http, $uibModal, UtilsAlertsFactory){

        var vm = this;


        vm.openUsuarioModal = function () {
            var modalInstance = $uibModal.open({
                templateUrl: "/usuarioModal.html",
                bindToController:true,
                controller: 'usuarioModalController',
                controllerAs:'vm',
                scope: $scope,
                size: 'lg',
                keyboard: false,
                backdrop: 'true'
            });
        }

    });
})();

