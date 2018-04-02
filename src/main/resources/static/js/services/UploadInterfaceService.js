(function () {
    'use strict';

    var appBordamax = angular.module("appBordamax");

    appBordamax.service('UploadInterfaceService', function($q,S3UploadService,$rootScope,Utils) {

    var sizeLimit = 10585760; // 10MB in Bytes
    var uploadProgress = 0;
    // return {
    this.upload = function(file,multiples,types) {
        console.log('---')
        var deferred = $q.defer();

        if(file) {
            // Perform File Size Check First
            var fileSize = Math.round(parseInt(file.size));
            if (fileSize > sizeLimit) {
                //toaster.error('Desculpe, o arquivo é muito grande <br/> Máximo: '  + this.fileSizeLabel() + ' tamanho de arquivo.','File Too Large');
                deferred.reject()
                return false;
            }
            //angular.forEach(file, function (file, key) {

            if(!this.verifyTypesFiles(Utils.validateTypeFile(file.type),types)){
                deferred.reject()
                //toaster.warning('Tipo de arquivo não aceito!');
                return false
            }

            console.log('.....')
            S3UploadService.Upload(file).then(function (result) {
                // Mark as success
                file.Success = true;
                //toaster.success({body: 'Upload realizado!', showCloseButton: true, timeout: 2000});
                $rootScope.nameAnexo = result;

                //Se multiplos arquivos
                if(multiples)
                    $rootScope.nameAnexos.push(result);
                // Reset The Progress Bar
                setTimeout(function() {
                    uploadProgress = 0;
                }, 2000);
                $rootScope.$broadcast('loading:hide');
                deferred.resolve(result);

            }, function (error) {
                console.log(error);
                // Mark the error
                //toaster.error('Ocorreu um erro',error);
                $rootScope.$broadcast('loading:hide');
                deferred.reject(error);
            }, function (progress) {
                // Write the progress as a percentage
                file.Progress = (progress.loaded / progress.total) * 100;
                uploadProgress = Math.round(progress.loaded / progress.total * 100);
                console.log(uploadProgress);
                $rootScope.$broadcast('loading:show','Enviando imagem '+uploadProgress+'%');
                //return uploadProgress;

            });
            //});
        }else {
            // No File Selected
            //toaster.error('Selecione algum arquivo!');
            $rootScope.$broadcast('loading:hide');
            deferred.resolve(null);
        }

        return deferred.promise;
    };
    this.fileSizeLabel = function () {
        // Convert Bytes To MB
        return Math.round(sizeLimit / 1024 / 1024) + 'MB';
    };
    this.verifyTypesFiles = function (typeFile,types) {
        var lenght = types.length,i,accept = false;
        if(typeFile)
            for(i = 0; i < lenght; i++){
                if(types[i] == typeFile){
                    accept = true;
                    break;
                }
            }
        return accept;
    }


    //};


});
})();
