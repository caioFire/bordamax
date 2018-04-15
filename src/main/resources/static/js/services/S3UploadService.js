
(function () {
    'use strict';

    var appS3UploadService = angular.module("appBordamax");

    appS3UploadService.service('S3UploadService', function ($q,ParamsService) {

    //this.Progress = 0;
    this.Upload = function (file) {
        var deferred = $q.defer();
        /** Get configs AWS **/
        ParamsService.getAwsParamsFixed().then(function (result) {
            // Us standard region
            AWS.config.region = 'us-west-2';
            AWS.config.update({ accessKeyId: result.accessKey, secretAccessKey: result.secretKey });
            var bucket = new AWS.S3({ params: { Bucket: result.bucket, maxRetries: 10 }, httpOptions: { timeout: 360000 }});

            /** Cria um hash único **/
            var uniqueString = function () {
                var text     = "";
                var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                for( var i=0; i < 16; i++ ) {
                    text += possible.charAt(Math.floor(Math.random() * possible.length));
                }
                return text;
            };

            var removeEspecialChars = function (palavra){
                var com_acento = 'áàãâäéèêëíìîïóòõôöúùûüçÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÖÔÚÙÛÜÇ´`^¨~ ';
                var sem_acento = 'aaaaaeeeeiiiiooooouuuucAAAAAEEEEIIIIOOOOOUUUUC------';
                var l,l2;
                for (l in palavra){
                    for (l2 in com_acento){
                        if (palavra[l] == com_acento[l2]){
                            palavra=palavra.replace(palavra[l],sem_acento[l2]);
                        }
                    }
                }
                return palavra;
            };
            /** Verificar nameAnexo **/
            var fileNameAnexo = removeEspecialChars(uniqueString()+'-caio-'+file.name);
            var params = {
                Bucket: result.bucket,
                Key: fileNameAnexo,
                ContentType:file.type,
                Body: file
            };
            var options = {
                // Part Size of 10mb
                partSize: 10 * 1024 * 1024,
                queueSize: 1,
                // Acesso para leitura
                ACL: 'public-read'
            };


            var uploader = bucket.upload(params, options, function (err, data) {
                if (err) {
                    deferred.reject(err);
                }
                deferred.resolve(fileNameAnexo);
            });
            uploader.on('httpUploadProgress', function (event) {
                deferred.notify(event);
            });

        },function (err) {
            console.log(err);
            deferred.reject(err);
        });

        return deferred.promise;
    };
});
})();
