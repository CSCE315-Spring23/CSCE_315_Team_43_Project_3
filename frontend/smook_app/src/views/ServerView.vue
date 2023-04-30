<script setup>
import Heading from '../components/Heading.vue';

import { reactive } from 'vue';
import Category from '../components/server/Category.vue'
import Transaction from '../components/server/Transaction.vue'
import Custom from '../components/server/Custom.vue'
import axios from 'axios';


const itemTypes = reactive([]);
async function getCatagories() {
  axios.get('/api/category', {
})
  .then(response => {
    const categories = response.data;
    for (let i = 0; i<categories.length; i++){
      if (categories[categories.length-i-1] == "Other")
      continue
      if (categories[categories.length-i-1] == "Enhance")
      continue
      itemTypes.push(categories[categories.length-i-1]);
    }
  })
  .catch(error => {
    console.error(error);
  });
}
getCatagories();
</script>

<template>
      <Heading />
      <!-- <button style="z-index: 10000;" @click="getCatagories">Hello</button> -->
  <main>
    <div id="main">
        <div id="orderPanel">
            <Category 
                v-for="item in itemTypes"
                :key="item"
                :catName="item"
            />
        </div>
        <Transaction/>
    </div>
  </main>
  <Custom/>
  <cartButton />
</template>
<style scoped>
main {
  width: 100%;
  height: 100%;
  position: absolute;
  top:0;
  left:0;
}
#main {
  height: 89%;
  width: 100%;
  position: absolute;
  top: 75px;
  display: flex;
}
#main header {
    height: 35%;
    background-color: lightblue;
    display: flex;
    justify-content: center;
    align-items: center;
}
#main header h1 {
    font-size: 100px;
    text-align: center;
}
#orderPanel {
    border: 4px black solid;
    /* width: 75%; */
    display: flex;
    justify-content: center;
}
#cartViewServer {
    width: 40%;
}
</style>
