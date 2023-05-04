<script setup>
    import VuetifyDatatable from "../../components/managerPanels/ExcessTable.vue";
    import NavBar from '../../components/managerPanels/Back.vue';
    import axios from 'axios';
    import { reactive } from 'vue'
    import Heading from '../../components/Heading.vue';

    let posts = reactive([]);
    async function getInventory() {
    await axios.get('https://smook-app.uc.r.appspot.com/api/ExcessReport', {
    })
      .then(response => {
        const excess = response.data;
        // posts = response.data;
        for (let i = 0; i< excess.length; i++){
          posts.push(excess[excess.length-i-1]);
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
    <aria-label="Back" NavBar/>
    <VuetifyDatatable :posts="posts" />
  </div>
</template>