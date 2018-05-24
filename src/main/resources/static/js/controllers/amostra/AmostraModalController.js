
/**
 * Created by fire on 20/03/18.
 */


(function () {
    'use strict';

    var appBordamax = angular.module("appAmostra");

    appBordamax.controller ("amostraModalController", function ($scope, $http, UploadInterfaceService,
                                                                $uibModalInstance, params,$q){

        var vm = this;

        vm.amostra = {
            status: true,
            cliente: null,
            localizacao: null,
            urlImagem:null
        };
        vm.inSave = false;


        if(params){
            console.log(params)
            vm.amostra = {
                id: params.id,
                codigo: params.codigo,
                descricao: params.descricao,
                portfolio: params.portfolio,
                status: params.status,
                cliente: params.cliente,
                localizacao: params.localizacao,
                urlImagem: "https://s3-us-west-1.amazonaws.com/bordamax/"+params.urlImagem
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

        vm.cadastrarAmostra = function (files) {
            vm.mensagem = false;
            let campos = validaCampos(vm.amostra), url =  'http://23.235.228.203/amostra/';

            if(!campos){
                vm.inSave = true;
                vm.upload(files,false,['img']).then(function (res) {
                    /** Após upload, prosseguir com save **/

                    let aux = angular.copy(vm.amostra);
                    /** Adicionando url ao json de envio **/
                    vm.amostra.urlImagem = res;

                    url += (params) ? 'update' : 'new';
                    $http({
                        method: 'POST',
                        url: url, data: vm.amostra
                    }).then(function successCallback(response) {
                        vm.inSave = false;
                        let retorno = validaRetorno(response.data);
                        if(retorno){
                            vm.mensagem = response.data.mensagem;
                        } else{
                            vm.fecharModal(response.data.mensagem);
                        }
                    }, function errorCallback(response) {
                        vm.inSave = false;
                        console.log(response.status);
                    });

                },function (err) {
                    /** Erro de upload, decidir se cancela ou continua o save**/
                    vm.mensagem = err;
                    vm.inSave = false;
                });

            } else{
                vm.mensagem = campos;
            }
        };

        vm.carregarClientes = function () {
            $http({
                method: 'GET',
                url: 'http://23.235.228.203/cliente/getAllByStatus?status=true'
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
                url: 'http://23.235.228.203/localizacao/getAllByStatus?status=true'
            }).then(function successCallback(response) {
                vm.localizacoes = response.data;
                // vm.amostra.localizacao = vm.localizacoes[0];
            }, function errorCallback(response) {
                console.log(response.status);
            });
        };


        /**
         *  @files
         *  @multiples
         *  @types => array : ex: ['img','xml']
         *  Retorna uma promise com o resultado final do upload
         *  **/
        vm.upload = function (files,multiples,types) {
            let deferred = $q.defer();
            UploadInterfaceService.upload( files, multiples, types).then(function (response) {
                /** Upload Completo **/
                vm.progress = 0;
                deferred.resolve(response);
            },function ( err ) {
                console.log(err);
                deferred.reject(err);
            },function (progress) {
                vm.progress = progress;
            });
            return deferred.promise;
        };



        vm.updateAmostra = function () {
            vm.mensagem = false;
            let campos = validaCampos(vm.amostra), url =  'http://23.235.228.203/amostra/';

            if(!campos){
                vm.inSave = true;

                url += (params) ? 'update' : 'new';
                $http({
                    method: 'POST',
                    url: url, data: vm.amostra
                }).then(function successCallback(response) {
                    vm.inSave = false;
                    let retorno = validaRetorno(response.data);
                    if(retorno){
                        vm.mensagem = response.data.mensagem;
                    } else{
                        vm.fecharModal(response.data.mensagem);
                    }
                }, function errorCallback(response) {
                    vm.inSave = false;
                    console.log(response.status);
                });

            } else{
                vm.mensagem = campos;
            }
        };





        vm.carregarClientes();
        vm.carregarLocalizacoes();

    });






})();


