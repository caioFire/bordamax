
/**
 * Created by fire on 20/03/18.
 */


(function () {
    'use strict';

    var appBordamax = angular.module("appAmostra");

    appBordamax.controller ("amostraModalController", function ($scope, $http, UploadInterfaceService,
                                                                $uibModalInstance, params){

        var vm = this;

        vm.amostra = {
            status: true,
            cliente: null,
            localizacao: null
        };


        if(params){
            console.log(params)
            vm.amostra = {
                id: params.id,
                codigo: params.codigo,
                descricao: params.descricao,
                portfolio: params.portfolio,
                status: params.status,
                cliente: params.cliente,
                localizacao: params.localizacao
            }
        }

        vm.fecharModal = function (param) {
            $uibModalInstance.close(param);
        };

        function validaCampos(dados) {
           if(!dados.codigo){
               return "Favor preencher os campos obrigatorios (*)";
           } else if(!dados.cliente){
               return "Favor preencher os campos obrigatorios (*)";
           } else if(!dados.localizacao){
               return "Favor preencher os campos obrigatorios (*)";
           } else{
               return false;
           }
        }

        function validaRetorno(dados) {
           if(dados.mensagem === "Já existe um registro cadastrado com esse nome!"){
               return true;
           } else{
               return false;
           }
        }

        vm.cadastrarAmostra = function () {
            vm.mensagem = false;
            let campos = validaCampos(vm.amostra);
            if(!campos){
                let url =  'http://localhost:8080/amostra/';
                url += (params) ? 'update' : 'new';
                $http({
                    method: 'POST',
                    url: url, data: vm.amostra
                }).then(function successCallback(response) {
                    let retorno = validaRetorno(response.data);
                    if(retorno){
                        vm.mensagem = response.data.mensagem;
                    } else{
                        vm.fecharModal(response.data.mensagem);
                    }
                }, function errorCallback(response) {
                    console.log(response.status);
                });
            } else{
                vm.mensagem = campos;
            }
        };

        vm.carregarClientes = function () {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/cliente/getAllByStatus?status=true'
            }).then(function successCallback(response) {
                vm.clientes = response.data;

                //console.log(vm.clientes);
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };

        vm.carregarLocalizacoes = function () {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/localizacao/getAllByStatus?status=true'
            }).then(function successCallback(response) {
                vm.localizacoes = response.data;
                // vm.amostra.localizacao = vm.localizacoes[0];
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };


        vm.upload = function (files,types) {
            UploadInterfaceService.upload( files[0], false, types).
            then(function (res) {
                console.log(res)
            },function ( err ) {
                console.log(err)
            },function (progress) {
                console.log(progress)
            })

        }

        vm.carregarClientes();
        vm.carregarLocalizacoes();

    });
})();


