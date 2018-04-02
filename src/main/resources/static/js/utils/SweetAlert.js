(function () {
    'use strict';

    var appBordamax = angular.module("appBordamax");

    appBordamax.factory('SweetAlertFactory', function (SweetAlert) {

    return{
        sweetAlert: function (config,callbackConfirm,callbackCancel) {
            SweetAlert.swal({
                    title: config.title,
                    html: true,
                    allowEscapeKey : true,
                    allowOutsideClick : true,
                    text: config.text,
                    type: config.type,
                    animation: "slide-from-bottom",
                    imageUrl:config.imageUrl,
                    showCancelButton: config.showCancelButton,
                    confirmButtonColor: "#1a7bb9",
                    confirmButtonText: (config.txtBtnConfirm)?config.txtBtnConfirm:"Enviar",
                    cancelButtonText: (config.txtBtnCancel)?config.txtBtnCancel:"Cancelar",
                    showLoaderOnConfirm: true,
                    closeOnConfirm: (config.closeConfirm)?true:false,
                    closeOnCancel: (config.closeCancel)?true:false },
                function (isConfirm) {
                    if (isConfirm) {
                        if(callbackConfirm){
                            callbackConfirm.forEach(function(element) {
                                element();
                            });
                        }

                    } else {
                        if(callbackCancel){
                            callbackCancel();
                        }else {
                            //SweetAlert.swal("Cancelado", "Atualizaçao cancelada.", "error");
                        }
                    }
                });

        }
    }
});
})();