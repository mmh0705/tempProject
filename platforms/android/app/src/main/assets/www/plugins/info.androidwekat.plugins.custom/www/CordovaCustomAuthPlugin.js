cordova.define("info.androidwekat.plugins.custom.CordovaCustomAuthPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'CordovaCustomAuthPlugin', 'coolMethod', [arg0]);
};

});
