/**
 * Informe um array e receba uma queryDSL para rest
 * @param data
 * @returns {string}
 */

(function () {
    'use strict';

    var appBordamax = angular.module("appBordamax");

    appBordamax.factory("Utils", function ( ) {

        return{
             buildQuery: function(data) {
                if (data || data == 0) {
                    var count = 0;
                    var query = '';
                    for (var key in data) {
                        if (count === 0) {
                            query += ((data[key] || data[key] === 0) ? '?' + key + '=' + data[key] : '?');
                        } else {
                            if (query === '?') {
                                query += ((data[key] || data[key] === 0) ? key + '=' + data[key] : '');
                            } else {
                                query += ((data[key] || data[key] === 0) ? '&' + key + '=' + data[key] : '');
                            }
                        }
                        count++;
                    }
                    return query;
                }
            },

            /** Check if an object is empty **/
            isEmpty: function (obj) {
                if (obj === null) return true;
                if (obj.length > 0) return false;
                if (obj > 0) return false;
                if (obj.length === 0) return true;
                for (var key in obj) {
                    if (hasOwnProperty.call(obj, key)) return false;
                }
                return true;
            },
            validateTypeFile: function (file) {
                var ext = file.substr(-3).toLowerCase();
                if (ext == 'png' || ext == 'jpg' || ext == 'peg' || ext == 'gif') {
                    return 'img';
                } else if (ext == 'pdf') {
                    return 'pdf';
                } else if (ext == 'mp4' || ext == '3gp' || ext == 'm4v' || ext == 'mov') {
                    return 'video';
                } else if (ext == 'xml') {
                    return 'xml';
                } else if (ext == 'txt') {
                    return 'txt'
                }
                else {
                    return false;
                }
            },

}
    })
})();