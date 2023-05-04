<script setup>
    import VuetifyDatatable from "../../components/managerPanels/MenuTable.vue";
    import NavBar from '../../components/managerPanels/Back.vue';
    import axios from 'axios';
    import { reactive } from 'vue'
    import Heading from '../../components/Heading.vue';

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
  <Heading true/>
  <br>
  <h1>Manager</h1>
  <div>
    <aria-label="Back" NavBar/>
    <VuetifyDatatable :posts="posts" />
  </div>
</template>