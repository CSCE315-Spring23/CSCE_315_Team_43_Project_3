<script setup>
    import VuetifyDatatable from "../../components/managerPanels/MenuTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
    import axios from 'axios';

    let posts = [];
    async function getMenu() {
    axios.get('http://localhost:8080/api/menu_items', {
    })
      .then(response => {
        posts = response.data;
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