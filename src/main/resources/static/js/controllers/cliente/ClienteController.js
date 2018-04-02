/**
 * Created by fire on 20/03/18.
 */

(function () {
    'use strict';

    var appCliente = angular.module("appCliente");

    appCliente.controller ("clienteController", function ($scope, $http, $uibModal, NgTableParams, ClienteService, Utils){
        var vm = this;
        vm.clientes = [];

        vm.limparFiltro = function () {
            vm.filtroNome = "";
            vm.tableParams.reload();
        }

        vm.openClienteModal = function (idCliente) {
            var modalInstance = $uibModal.open({
                templateUrl: "/clienteModal.html",
                bindToController: true,
                controller: 'clienteModalController',
                controllerAs: 'vm',
                scope: $scope,
                size: 'sm',
                keyboard: false,
                backdrop: 'true',
                resolve:
                    {'params':function(){
                        return idCliente;
                    }}
            });modalInstance.result.then(function (param) {
                if(param)
                    vm.tableParams.reload();
            })
        }

        vm.excluirCliente = function (id) {
            $http({
                method: 'POST',
                url: 'http://localhost:8091/cliente/delete', data: id
            }).then(function successCallback(response) {
                vm.tableParams.reload();
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.excluir = function(id) {
            if(window.confirm("Deseja excluir este registro?")){
                vm.excluirCliente(id);
            }
        }

        vm.reloadTable =function(){
            vm.tableParams.reload();
        }

        vm.tableParams = new NgTableParams({}, {

            getData: function (params) {
                console.log(params.filter());
                let dataFilter = {
                    paginaAtual: (params.page() - 1),
                    tamanhoPagina: params.count(),
                    ascendente: (params.orderBy().length && (params.orderBy()) && (params.orderBy()[0]).substring(0, 1) === '-') ? 'false' : 'true',
                    campoOrderBy: (params.orderBy().length && (params.orderBy())) ? (params.orderBy()[0]).substring(1) : 'nome',
                    nome: vm.filtroNome
                };

                /** Requisição **/
                return ClienteService.getCliente(dataFilter).then(function (response) {
                    vm.clientes = response;
                    params.total(vm.clientes.length);
                    console.log(vm.clientes)
                    return vm.clientes
                },function (err) {
                    console.log(err)
                })


            }
        });

    });
})();

