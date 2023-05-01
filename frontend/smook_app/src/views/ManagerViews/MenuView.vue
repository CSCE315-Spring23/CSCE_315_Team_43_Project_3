<script setup>
    import VuetifyDatatable from "../../components/managerPanels/MenuTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
    import axios from 'axios';
    import { reactive } from 'vue'

    let posts = reactive([]);
    async function getMenu() {
    await axios.get('https://smook-app.uc.r.appspot.com/api/menu_items', {
    })
      .then(response => {
        const menu = response.data;
        for (let i = 0; i< menu.length; i++){
          posts.push(menu[menu.length-i-1]);
        }
        console.log(posts);
      })
      .catch(error => {
        console.error(error);
      });
    }
    getMenu();
</script>

<template>
  <h1>Manager</h1>
  <div>
    <VuetifyDatatable :posts="posts" />
  </div>
</template>