(function () {
    'use strict';

    var appBordamax = angular.module("appBordamax");
    appBordamax.service('UploadInterfaceService', function ($q, S3UploadService, $rootScope, Utils) {


            this.upload = function (file, multiples, types) {

                var sizeLimit = 2646440 ; // 10585760=10MB in Bytes / 2646440=2,5MB
                var uploadProgress = 0;
                var deferred = $q.defer();

                    if (file) {

                        // Perform File Size Check First
                        var fileSize = Math.round(parseInt(file.size));
                        if (fileSize > sizeLimit) {
                            deferred.reject('Desculpe, o arquivo é muito grande <br/> Máximo: ' + Utils.fileSizeLabel(sizeLimit) + ' tamanho de arquivo.')
                            return false;
                        }
                        //angular.forEach(file, function (file, key) {

                        if (!Utils.verifyTypesFiles(Utils.validateTypeFile(file.type), types)) {
                            deferred.reject('Tipo de arquivo não aceito!');
                            return false
                        }

                        S3UploadService.Upload(file).then(function (result) {
                            // Mark as success
                            file.Success = true;
                            //toaster.success({body: 'Upload realizado!', showCloseButton: true, timeout: 2000});
                            $rootScope.nameAnexo = result;

                            //Se multiplos arquivos
                            if (multiples)
                                $rootScope.nameAnexos.push(result);
                            // Reset The Progress Bar
                            setTimeout(function () {
                                uploadProgress = 0;
                            }, 1000);
                            deferred.resolve(result);

                        }, function (error) {
                            console.log(error);
                            // Mark the error
                            //toaster.error('Ocorreu um erro',error);
                            deferred.reject(error);
                        }, function (progress) {

                            file.Progress = (progress.loaded / progress.total) * 100;
                            uploadProgress = Math.round(progress.loaded / progress.total * 100);
                            deferred.notify(uploadProgress)

                        });
                        //});
                    } else {
                        // No File Selected
                        deferred.reject('Selecione algum arquivo!');
                    }


                return deferred.promise;
            }



    });
})();
