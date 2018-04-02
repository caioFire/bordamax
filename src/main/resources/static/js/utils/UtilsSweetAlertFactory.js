(function () {
    'use strict';

    var appBordamax = angular.module("appBordamax");

    appBordamax.factory('UtilsAlertsFactory', function (SweetAlertFactory) {
        return {
            errorRequest: function (err) {
                /** Chamando sweet alert **/
                var config = {
                    "title": "Ocorreu um erro!",
                    "text": "<h3>Não se preocupe! Apenas comunique ao analista responsável</h3>" +
                    "<textarea readonly class='form-control' rows='4' style='width: 100%' cols='auto'>" + JSON.stringify(err) + "</textarea>",
                    "type": "error",
                    "showCancelButton": false,
                    "closeConfirm": true,
                    "txtBtnConfirm": "Voltar"
                };
                SweetAlertFactory.sweetAlert(config);

            },
            formInvalid: function () {
                /** Chamando sweet alert **/
                var config = {
                    "title": "Dados incompletos!",
                    "text": "Preencha corretamente o formulário!",
                    "type": "warning",
                    "showCancelButton": false,
                    "closeConfirm": true,
                    "txtBtnConfirm": "Voltar"
                };
                SweetAlertFactory.sweetAlert(config);

            },
            returnErrorServer: function (err) {
                /** Chamando sweet alert **/
                var config = {
                    "title": "Houve um erro!",
                    "text": "<h3>Não se preocupe! Apenas comunique ao analista responsável</h3>" +
                    "<textarea readonly class='form-control' rows='4' cols='auto' style='width: 100%'>" + JSON.stringify(err) + "</textarea>",
                    "type": "error",
                    "showCancelButton": false,
                    "closeConfirm": true,
                    "txtBtnConfirm": "Voltar"
                };
                SweetAlertFactory.sweetAlert(config);

            },
            returnSuccess: function (message, backPage, paramsState) {
                /** Chamando sweet alert **/
                var config = {
                    "title": "Sucesso!",
                    "text": message,
                    "type": "success",
                    "showCancelButton": false,
                    "closeConfirm": true,
                    "txtBtnConfirm": "Voltar"
                };
                var returnPage = function () {
                    if (backPage)
                        $state.go(backPage, paramsState);
                };
                SweetAlertFactory.sweetAlert(config, returnPage(), returnPage());

            },
            returnWarning: function (title, message) {
                /** Chamando sweet alert **/
                var config = {
                    "title": title,
                    "text": message,
                    "type": "warning",
                    "showCancelButton": false,
                    "closeConfirm": true,
                    "txtBtnConfirm": "Voltar"
                };
                SweetAlertFactory.sweetAlert(config);

            },
            callback: function (messages, btnsMessages, type, callback, urlImage) {
                /** Chamando sweet alert **/
                var config = {
                    "title": messages.title,
                    "text": messages.text,
                    "type": type,
                    "showCancelButton": (btnsMessages.cancel) ? true : false,
                    "closeConfirm": true,
                    "txtBtnCancel": btnsMessages.cancel,
                    "txtBtnConfirm": btnsMessages.confirm,
                    "closeCancel": true,
                    "imageUrl": urlImage
                };
                SweetAlertFactory.sweetAlert(config, callback);


            }
        }
    });

})();