<script setup>
import Heading from '../components/Heading.vue';
import Login from '../components/Login.vue';

import { useRouter } from 'vue-router'

const router = useRouter();

function menu() {
  router.push('/menuBoard')
}
function order() {
  router.push('/customer')
}
</script>

<template>
      <Heading />
  <main>
    <div id="main">
      <div id="buttons">
        <button @click="menu()">View Menu</button>
        <button @click="order">Order</button>
      </div>
    </div>
  </main>
</template>
<style scoped>
/* main {
  background-image: url(../assets/SmoothieBackground.jpg);
  height: 1000;
} */
#main {
  margin-top: 80px;
  /* height: 1000px; */
}
#buttons {
  margin-top: 30%;
}
  button{
    background-color: rgb(208, 18, 18);
        color: white;
        font-size: 20px;
        padding: 8px 23px;
        margin-top: 0;
        display: block;
        margin-bottom: 10px;
        border-radius: 10px;
        border: black 3px solid;
        box-sizing: border-box;
        cursor: pointer;
  }
  button:hover {
    background-color: white;
    color: black;
  }
</style>
