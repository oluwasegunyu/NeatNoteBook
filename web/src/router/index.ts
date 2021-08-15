import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import home from '../views/home.vue'
import about from '../views/about.vue'
import adminEbook from '../views/admin/adminEbook.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: home
  },
  {
    path: '/about',
    name: 'About',
    component: about
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/about.vue')
  },
  {
    path: '/admin/ebook',
    name: 'adminEbook',
    component: adminEbook
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
