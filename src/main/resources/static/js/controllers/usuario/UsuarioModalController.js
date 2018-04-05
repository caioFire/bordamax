/**
 * Created by fire on 24/03/18.
 */

/**
 * Created by fire on 20/03/18.
 */


(function () {
    'use strict';

    var appUsuario = angular.module("appUsuario");

    appUsuario.controller ("usuarioModalController", function ($scope, $http, $uibModalInstance, params){

        var vm = this;
        vm.usuario = {
            status: true
        };

        if(params){
            vm.usuario = {
                id: params.id,
                nome: params.nome,
                identificacao: params.identificacao,
                senha: params.senha,
                status: params.status
            }
        }

        vm.fecharModal = function (param) {
            $uibModalInstance.close(param);
        };

        function validaCampos(dados) {
            if(!dados.nome){
                return "Favor preencher os campos obrigatorios (*)";
            } else if(!dados.senha){
                return "Favor preencher os campos obrigatorios (*)";
            } else if(!dados.identificacao){
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

        vm.cadastrarUsuario = function () {
            vm.mensagem = false;
            let campos = validaCampos(vm.usuario);
            if(!campos){
                let url =  'http://localhost:8091/usuario/';
                url += (params) ? 'update' : 'new';
                $http({
                    method: 'POST',
                    url: url, data: vm.usuario
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
                vm.mensagem = retorno;
            }

        };


    });
})();
