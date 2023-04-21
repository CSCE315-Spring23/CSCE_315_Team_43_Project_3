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
      component: () => import('../views/CustomerView.vue')
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
      path: '/Inventory',
      name: 'inventory',
      component: () => import('../views/ManagerViews/InventoryView.vue')
    },
    {
      path: '/OrderInventory',
      name: 'orderInventory',
      component: () => import('../views/ManagerViews/OrderInventory.vue')
    },
    {
      path: '/Menu',
      name: 'menu',
      component: () => import('../views/ManagerViews/MenuView.vue')
    },
    {
      path: '/EditMenu',
      name: 'editMenu',
      component: () => import('../views/ManagerViews/EditMenuView.vue')
    },
    {
      path: '/AddMenu',
      name: 'addMenu',
      component: () => import('../views/ManagerViews/AddMenuView.vue')
    },
    {
      path: '/OrderInventory',
      name: 'orderInventory',
      component: () => import('../views/ManagerViews/OrderInventory.vue')
    },
    {
      path: '/AddInventory',
      name: 'addInventory',
      component: () => import('../views/ManagerViews/AddInventoryView.vue')
    },
    {
      path: '/XReport',
      name: 'xReport',
      component: () => import('../views/ManagerViews/XReportView.vue')
    },
    {
      path: '/ZReport',
      name: 'zReport',
      component: () => import('../views/ManagerViews/ZReportView.vue')
    },
  ]
})

export default router
