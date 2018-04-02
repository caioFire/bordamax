/**
 * Created by fire on 20/03/18.
 */

(function () {
    'use strict';

    var appLocalizacao = angular.module("appLocalizacao");

    appLocalizacao.controller ("localizacaoController", function ($scope, $http, $uibModal, NgTableParams, LocalizacaoService, Utils) {

        var vm = this;
        vm.localizacoes = [];

        vm.limparFiltro = function () {
            vm.filtroNome = "";
            vm.tableParams.reload();
        }

        vm.openLocalizacaoModal = function (idLocalizacao) {
            var modalInstance = $uibModal.open({
                templateUrl: "/localizacaoModal.html",
                bindToController: true,
                controller: 'localizacaoModalController',
                controllerAs: 'vm',
                scope: $scope,
                size: 'sm',
                keyboard: false,
                backdrop: 'true',
                resolve:
                    {'params':function(){
                        return idLocalizacao;
                    }}
            });modalInstance.result.then(function (param) {
                if(param)
                    vm.tableParams.reload();
            })
        }

        vm.excluirLocalizacao = function (id) {
            $http({
                method: 'POST',
                url: 'http://localhost:8091/localizacao/delete', data: id
            }).then(function successCallback(response) {
                vm.tableParams.reload();
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.excluir = function(id) {
            if(window.confirm("Deseja excluir este registro?")){
                vm.excluirLocalizacao(id);
            }
        }

        vm.reloadTable =function(){
            vm.tableParams.reload();
        }

        vm.tableParams = new NgTableParams({}, {

            getData: function (params) {
                let dataFilter = {
                    paginaAtual: (params.page() - 1),
                    tamanhoPagina: params.count(),
                    ascendente: (params.orderBy().length && (params.orderBy()) && (params.orderBy()[0]).substring(0, 1) === '-') ? 'false' : 'true',
                    campoOrderBy: (params.orderBy().length && (params.orderBy())) ? (params.orderBy()[0]).substring(1) : 'nome',
                    nome: vm.filtroNome
                };
                console.log(dataFilter);

                /** Requisição **/
                return LocalizacaoService.getLocalizacao(dataFilter).then(function (response) {
                    vm.localizacoes = response;
                    params.total(vm.localizacoes.length);
                    //console.log(vm.localizacoes)
                    return vm.localizacoes
                },function (err) {
                    console.log(err)
                })


            }
        });

    });
})();

