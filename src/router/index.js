import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Splash from '../views/SplashScreen.vue'
import Home from '../views/Home.vue'
import SMS from '../views/SMS.vue'
import Analysis from '../views/Analysis.vue'
import SMSscore from '../views/SMSscore.vue'
import Login from '../views/Login.vue'

Vue.use(Router)
export default new Router({
  routes: [
    
    {
      path: '/',
      name: 'Splash',
      component: Splash
    },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/sms',
    name: 'SMS',
    component: SMS
  },
  {
    path: '/analysis',
    name: 'Analysis',
    component : Analysis
  },
  {
    path: '/smsscore',
    name: 'SMSscore',
    component: SMSscore
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  ]
})
