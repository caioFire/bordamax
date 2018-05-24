/**
 * Created by fire on 20/03/18.
 */

(function () {
    'use strict';

    var appAmostra = angular.module("appAmostra");

    appAmostra.controller ("amostraController", function ($scope, $http, $uibModal, NgTableParams, AmostraService, Utils, UtilsAlertsFactory){

        var vm = this;
        vm.amostras = [];

        vm.limparFiltro = function () {
            vm.filtroCodigo = "";
            vm.tableParams.reload();
        }

        vm.openAmostraModal = function (idAmostra) {
            var modalInstance = $uibModal.open({
                templateUrl: "/amostraModal.html",
                bindToController: true,
                controller: 'amostraModalController',
                controllerAs: 'vm',
                scope: $scope,
                size: 'lg',
                keyboard: false,
                backdrop: 'true',
                resolve:
                    {'params':function(){
                        return idAmostra;
                    }}
            });modalInstance.result.then(function (param) {
                if(param) {
                    vm.tableParams.reload();
                    vm.mensagem = param;
                }
            })
        }

        function validaRetorno(dados) {
            if(dados.mensagem === "Registro excluido com sucesso!"){
                vm.mensagem = dados.mensagem;
            } else{
                false;
            }
        }

        vm.excluirAmostra = function (id) {
            $http({
                method: 'POST',
                url: 'http://23.235.228.203/amostra/delete', data: id
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
                    vm.excluirAmostra(id);
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
                    campoOrderBy: (params.orderBy().length && (params.orderBy())) ? (params.orderBy()[0]).substring(1) : 'codigo',
                    codigo: vm.filtroCodigo
                };

                /** Requisição **/
                return AmostraService.getAmostra(dataFilter).then(function (response) {
                    params.total(response.qtdeRegistros);
                    vm.amostras = response.listaAmostras;
                    return vm.amostras
                },function (err) {
                    console.log(err)
                })

            }
        });

        vm.openAmostraViewModal = function (Amostra) {
            var modalInstance = $uibModal.open({
                templateUrl: "/amostraViewModal.html",
                bindToController: true,
                controller: 'amostraViewModalController',
                controllerAs: 'vm',
                scope: $scope,
                size: 'lg',
                keyboard: false,
                backdrop: 'true',
                resolve:
                    {'params':function(){
                        return Amostra;
                    }}
            });
            // modalInstance.result.then(function (param) {
            // })
        }

    });
})();
