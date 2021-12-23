cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "info.androidabcd.plugins.custom.CordovaCustomPlugin",
      "file": "plugins/info.androidabcd.plugins.custom/www/CordovaCustomPlugin.js",
      "pluginId": "info.androidabcd.plugins.custom",
      "clobbers": [
        "cordova.plugins.CordovaCustomPlugin"
      ]
    }
  ];
  module.exports.metadata = {
    "info.androidabcd.plugins.custom": "1.0.0"
  };
});