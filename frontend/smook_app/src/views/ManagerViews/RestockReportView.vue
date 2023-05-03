<script setup>
    import VuetifyDatatable from "../../components/managerPanels/RestockTable.vue";
    import NavBar from '../../components/managerPanels/Back.vue';
    import axios from 'axios';
    import { reactive } from 'vue'
    import Heading from '../../components/Heading.vue';

    let posts = reactive([]);
    async function getInventory() {
    await axios.get('https://smook-app.uc.r.appspot.com/api/RestockReport', {
    })
      .then(response => {
        const restock = response.data;
        // posts = response.data;
        for (let i = 0; i< restock.length; i++){
          posts.push(restock[restock.length-i-1]);
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
  <Heading true/>
  <br>
  <h1>Manager</h1>
  <div>
    <NavBar/>
    <VuetifyDatatable :posts="posts" />
  </div>
</template>