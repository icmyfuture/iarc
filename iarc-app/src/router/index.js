import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Next from '@/components/Next'
import ajax from '@/util/ajax'

Vue.use(Router)
Vue.prototype.$ajax = ajax.fget

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }, {
      path: '/next',
      name: 'next',
      component: Next
    }
  ]
})
