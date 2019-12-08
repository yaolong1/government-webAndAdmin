// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Axios from "axios"
import "./style/index.scss"
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import moment from 'moment'
import bcrypt from 'bcryptjs';
import AxiosPlugin from "./plugin/AxiosPlugin";


Vue.use(bcrypt);
Vue.use(mavonEditor);
Vue.use(moment);
Vue.prototype.$axios = Axios;
Vue.use(AxiosPlugin)
Vue.use(Element);


new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
