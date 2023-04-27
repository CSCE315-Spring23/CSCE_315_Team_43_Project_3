<script setup>
    import VuetifyDatatable from "../../components/managerPanels/InventoryTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
    import axios from 'axios';

    let posts = [];
    async function getInventory() {
    axios.get('http://localhost:8080/api/validInventory', {
    })
      .then(response => {
        posts = response.data;
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