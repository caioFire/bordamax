/**
 * Created by fire on 24/03/18.
 */

/**
 * Created by fire on 20/03/18.
 */


(function () {
    'use strict';

    var appBordamax = angular.module("appLocalizacao");

    appBordamax.controller ("localizacaoModalController", function ( $http, $uibModalInstance, params){


        var vm = this;
        vm.localizacao = {
            status: true
        };

        if(params){
            vm.localizacao = {
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

        vm.cadastrarLocalizacao = function () {
            vm.mensagem = false;
            let campos = validaCampos(vm.localizacao);
            if(!campos) {
                let url = 'http://localhost:8091/localizacao/';
                url += (params) ? 'update' : 'new';
                $http({
                    method: 'POST',
                    url: url, data: vm.localizacao
                }).then(function successCallback(response) {
                    let retorno = validaRetorno(response.data);
                    if (retorno) {
                        vm.mensagem = response.data.mensagem;
                    } else {
                        vm.fecharModal(response.data.mensagem);
                    }
                }, function errorCallback(response) {
                    console.log(response.status);
                });
            } else{
                vm.mensagem = campos;
            }
        };

    });
})();


