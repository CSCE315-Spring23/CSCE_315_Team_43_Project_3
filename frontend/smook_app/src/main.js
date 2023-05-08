import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import { createPinia } from 'pinia'
import {useCartStore} from '@/stores/CartStore'

loadFonts()

createApp(App)
  .use(router)
  .use(vuetify)
  .use(createPinia())
  .mount('#app')

  const cartStore = useCartStore();