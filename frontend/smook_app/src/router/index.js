import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/customer',
      name: 'customer',
      component: () => import('../views/CustomerView.vue'),
      // children: [
      //   {
      //     // UserProfile will be rendered inside User's <router-view>
      //     // when /user/:id/profile is matched
      //     path: 'OrderPage',
      //     component: () => import('../views/OrderView.vue'),
      //   }
      // ]
    },
    {
      path: '/server',
      name: 'server',
      component: () => import('../views/ServerView.vue')
    },
    {
      path: '/manager',
      name: 'manager',
      component: () => import('../views/ManagerView.vue')
    },
    {
      path: '/menuBoard',
      name: 'menuBoard',
      component: () => import('../views/MenuBoard.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/customer/OrderPage',
      name: 'order',
      component: () => import('../views/OrderView.vue')
    },
    {
      path: '/customer/cart',
      name: 'cart',
      component: () => import('../views/CartView.vue')
    }
  ]
})

export default router
