<script setup>
import Heading from '../components/Heading.vue';

import { reactive } from 'vue';
import Category from '../components/customer/Category.vue'
import Smoothie from '../components/customer/Smoothie.vue'
import cartButton from '../components/customer/cartButton.vue'
import axios from 'axios';


const itemTypes = reactive(['Feel Energized', 'Get Fit','Manage Weight','Have Fun']);

// axios.get('http://localhost:8080/category')
//   .then(response => {
//     const categories = response.data;
//     console.log(categories); // Prints the array of strings to the console
//   })
//   .catch(error => {
//     console.error(error);
//   });
</script>

<template>
      <Heading />
  <main>
    <div id="main">
        <header>
            <h1>Smoothie Time!!!</h1>
        </header>
        <div id="orderPanel">
            <Category 
                v-for="item in itemTypes"
                :key="item"
                :catName="item"
            />
        </div>
    </div>
  </main>
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
    padding: 3%;
    padding-top: 5px;
    padding-bottom: 0;
    margin-bottom: 30px;
}
</style>
