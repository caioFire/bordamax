/**
 * Created by fire on 20/03/18.
 */

(function () {
    'use strict';

    var appHome = angular.module("appHome");

    appHome.controller ("homeController", function ($scope, $http){

        var vm = this;
        vm.amostras = {};
        vm.clientes = {};
        vm.localizacoes = {};
        vm.usuarios = {};

        vm.indicadorAmostras = function () {
            let headers = new Headers();
            headers.append('Content-Type', 'application/json', 'text/plain');
            $http({
                method: 'GET',
                url: 'http://18.219.249.32:8080/amostra/getIndicador',
                headers:{headers:headers}
            }).then(function successCallback(response) {
                vm.amostras = response.data;
                return vm.amostras
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.indicadorClientes = function () {
            $http({
                method: 'GET',
                url: 'http://18.219.249.32:8080/cliente/getIndicador'
            }).then(function successCallback(response) {
                vm.clientes = response.data;
                return vm.clientes
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.indicadorLocalizacao = function () {
            $http({
                method: 'GET',
                url: 'http://18.219.249.32:8080/localizacao/getIndicador'
            }).then(function successCallback(response) {
                vm.localizacoes = response.data;
                return vm.localizacoes
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.indicadorUsuarios = function () {
            $http({
                method: 'GET',
                url: 'http://18.219.249.32:8080/usuario/getIndicador'
            }).then(function successCallback(response) {
                vm.usuarios = response.data;
                return vm.usuarios
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.indicadorAmostras();
        vm.indicadorClientes();
        vm.indicadorLocalizacao();
        vm.indicadorUsuarios();
    });
})();

