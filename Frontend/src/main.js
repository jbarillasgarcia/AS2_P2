import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import VueRouter from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'

import PublicPage from './components/PublicPage'
import Start from './components/Start/Start'
import Users from './components/Users/Users'
import Hardware from './components/Hardware/Hardware'


const routes=new VueRouter({
  base:"/",
  mode:"history",
  routes:[
    {path:"/",component:PublicPage,children:[
      {path:"",component:Start,name:"Inicio"},
      {path:"/Users",component:Users,name:"Usuarios"},
      {path:"/Hardware",component:Hardware,name:"Hardware"}
    ]}
  ]
});

const baseURL="http://localhost:8081";

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(VueAxios,axios)

new Vue({
  router:routes,
  vuetify,
  render: h => h(App),
  beforeCreate(){
    Vue.prototype.$axios=axios;
    Vue.prototype.$webserviceBaseURL=baseURL;
  }
}).$mount('#app')
