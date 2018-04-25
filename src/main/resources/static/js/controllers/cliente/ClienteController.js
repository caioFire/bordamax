/**
 * Created by fire on 20/03/18.
 */

(function () {
    'use strict';

    var appCliente = angular.module("appCliente");

    appCliente.controller ("clienteController", function ($scope, $http, $uibModal, NgTableParams, ClienteService, Utils, UtilsAlertsFactory){
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
                if (param){
                    vm.tableParams.reload();
                    vm.mensagemSucesso = param;
                }
            })
        }

        function validaRetorno(dados) {
            if(dados.mensagem === "Registro excluido com sucesso!"){
                vm.mensagemAlerta = null;
                vm.mensagemSucesso = dados.mensagem;
            } else {
                vm.mensagemSucesso = null;
                vm.mensagemAlerta = dados.mensagem;
            }
        }

        vm.excluirCliente = function (id) {
            $http({
                method: 'POST',
                url: 'http://18.219.249.32:8080/cliente/delete', data: id
            }).then(function successCallback(response) {
                validaRetorno(response.data);
                vm.tableParams.reload();
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.excluir = function(id) {
            UtilsAlertsFactory.callback(
                {'title': '<h4>' + "Deseja excluir este registro?" + '</h4>', 'text':""},
                {'confirm': 'Sim','cancel':'Não'},
                '', [function () {
                    vm.excluirCliente(id);
                }],
                ''
                // 'img/icones/icon-app-notificacao.png'
            );
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
                    params.total(response.qtdeRegistros);
                    vm.clientes = response.listaCliente;
                    return vm.clientes
                },function (err) {
                    console.log(err)
                })


            }
        });

    });
})();

