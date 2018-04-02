/**
 * Created by fire on 24/03/18.
 */

/**
 * Created by fire on 20/03/18.
 */


(function () {
    'use strict';

    var appBordamax = angular.module("appCliente");

    appBordamax.controller ("clienteModalController", function ($scope, $http, $uibModalInstance, params){

        var vm = this;
        vm.cliente = {
            status: true
        };

        if(params){
            vm.cliente = {
                id: params.id,
                nome: params.nome,
                status: params.status
            }
        }

        vm.fecharModal = function (param) {
            $uibModalInstance.close(param);
        };

        function validaCampos(dados) {
            if(!dados.nome){
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

        vm.cadastrarCliente = function () {
            vm.mensagem = false;
            let campos = validaCampos(vm.cliente);
            if(!campos){
                let url =  'http://localhost:8091/cliente/';
                url += (params) ? 'update' : 'new';
                $http({
                    method: 'POST',
                    url: url, data: vm.cliente
                }).then(function successCallback(response) {
                    let retorno = validaRetorno(response.data);
                    if(retorno){
                        vm.mensagem = response.data.mensagem;
                    } else{
                        vm.fecharModal(true);
                    }                }, function errorCallback(response) {
                    console.log(response.status);
                });
            } else{
                vm.mensagem = retorno;
            }

        };


    });
})();
