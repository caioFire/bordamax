
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

            vm.amostra = {
                id: params.id,
                codigo: params.codigo,
                descricao: params.descricao,
                portfolio: params.portfolio,
                status: params.status,
                cliente: params.cliente,
                localizacao: params.localizacao,
                //urlImagem: "https://s3-us-west-2.amazonaws.com/memoriasviagens/"+params.urlImagem
                urlImagem: "https://s3-us-west-1.amazonaws.com/bordamax/"+params.urlImagem
            }
        }

        vm.fecharModal = function (param) {
            $uibModalInstance.close(param);
        };


    });
})();


