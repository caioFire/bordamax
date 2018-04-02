/**
 * Created by fire on 24/03/18.
 */

/**
 * Created by fire on 20/03/18.
 */


(function () {
    'use strict';

    var appUsuario = angular.module("appUsuario");

    appUsuario.controller ("usuarioModalController", function ($scope, $http, $uibModalInstance){

        var vm = this;
        vm.usuario = {
            status: true
        };

        vm.fecharModal = function () {
            $uibModalInstance.dismiss('cancel param');
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

        vm.cadastrarUsuario = function () {
            vm.mensagem = false;
            let campos = validaCampos(vm.usuario);
            if(!campos) {
                $http({
                    method: 'POST',
                    url: 'http://localhost:8091/usuario/new', data: vm.usuario
                }).then(function successCallback(response) {
                    let retorno = validaRetorno(response.data);
                    if(retorno){
                        vm.mensagem = response.data.mensagem;
                    } else {
                        vm.fecharModal(true);
                    }
                },function errorCallback(response) {
                    console.log(response.status);
                });
                vm.fecharModal();
            } else{
                vm.mensagem = campos;
            }
        };

    });
})();


