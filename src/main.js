import $ from "jquery";

import Vue from "vue";
import App from "./App.vue";
import router from "./router";
// import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
// import "bootstrap/dist/css/bootstrap.css";
// import "bootstrap-vue/dist/bootstrap-vue.css";
import Donut from 'vue-css-donut-chart';
import 'vue-css-donut-chart/dist/vcdonut.css';
import VueBottomSheet from "@webzlodimir/vue-bottom-sheet";

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

const ENV = { isNative: false };

const loadNativeScripts = async () =>
  new Promise((resolve, reject) =>
    $.getScript("cordova.js", resolve).fail((xhr, s, e) => {
      reject(e);
    })
  );

function onServiceReady() {
  // Vue.use(IconsPlugin);
  Vue.use(Donut);
  Vue.use(VueBottomSheet);

  Vue.use(Vuetify)
  
  
  Vue.config.productionTip = false;
  /* eslint-disable no-new */
  new Vue({
    el: "#app",
    router,
    vuetify : new Vuetify(),
    components: { App },
    template: "<App/>",
  });
}

const onDeviceReady = () => {
  console.log("Running Cordova" + cordova.platformId + "@" + cordova.version);
  document.addEventListener("backbutton", function(e){  
    e.preventDefault();
    navigator.app.exitApp();
  }, false);
  onServiceReady();
};

const onDocumentReady = () => {
  loadNativeScripts().then(
    () => {
      // cordova.js file found
      ENV.isNative = true;
      document.addEventListener("deviceready", onDeviceReady, false);
     
    },
    (e) => {
      ENV.isNative = false;
      console.log("Running Browser");
      onServiceReady();
    }
  );
};

document.addEventListener("DOMContentLoaded", onDocumentReady, false);
