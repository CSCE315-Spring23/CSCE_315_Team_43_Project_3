<script setup>
    import VuetifyDatatable from "../../components/managerPanels/ZTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
    import axios from 'axios';
    import { reactive } from 'vue'

    let posts = reactive([]);
    async function getInventory() {
    await axios.get('https://smook-app.uc.r.appspot.com/api/ZReport', {
    })
      .then(response => {
        const z = response.data;
        // posts = response.data;
        for (let i = 0; i< z.length; i++){
          posts.push(z[z.length-i-1]);
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