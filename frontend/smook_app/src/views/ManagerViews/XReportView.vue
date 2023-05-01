<script setup>
    import VuetifyDatatable from "../../components/managerPanels/XTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
    import axios from 'axios';
    import { reactive } from 'vue'

    let posts = reactive([]);
    async function getInventory() {
    await axios.get('https://smook-app.uc.r.appspot.com/api/XReport', {
    })
      .then(response => {
        const x = response.data;
        // posts = response.data;
        for (let i = 0; i< x.length; i++){
          posts.push(x[x.length-i-1]);
        }
        console.log(posts);
      })
      .catch(error => {
        console.error(error);
      });
    }
    getInventory();
</script>

<template>
  <h1>Manager</h1>
  <div>
    <VuetifyDatatable :posts="posts" />
  </div>
</template>