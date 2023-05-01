<script setup>
    import VuetifyDatatable from "../../components/managerPanels/InventoryTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
    import axios from 'axios';
    import { reactive } from 'vue'

    let posts = reactive([]);
    async function getInventory() {
    await axios.get('https://smook-app.uc.r.appspot.com/api/validInventory', {
    })
      .then(response => {
        const inventory = response.data;
        // posts = response.data;
        for (let i = 0; i< inventory.length; i++){
          posts.push(inventory[inventory.length-i-1]);
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