
/**
 * Created by fire on 20/03/18.
 */


(function () {
    'use strict';

    var appBordamax = angular.module("appAmostra");

    appBordamax.controller ("amostraViewModalController", function ($scope, $http,
                                                                $uibModalInstance, params){

        var vm = this;


        if(params){
            console.log(params)
            console.log("teste")

            vm.amostra = {
                id: params.id,
                codigo: params.codigo,
                descricao: params.descricao,
                portfolio: params.portfolio,
                status: params.status,
                cliente: params.cliente,
                localizacao: params.localizacao,
                urlImagens: param.urlImagens
            }
        }

        vm.fecharModal = function (param) {
            $uibModalInstance.close(param);
        };



    });
})();


