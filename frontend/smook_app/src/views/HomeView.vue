<script setup>
import Heading from '../components/Heading.vue';
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
        <h2>NURTURING PASSION.<br>NOURISHING PURPOSE.</h2>
        <div id="but2">
        <button @click="menu()">View Menu</button>
        <button @click="order">Order</button>
      </div>
      </div>
    </div>
  </main>
</template>
<style scoped>
/* main {
  background-image: url(../assets/SmoothieBackground.jpg);
  height: 1000;
} */
main {
  width: 100%;
  height: 100%;
  position: absolute;
  top:0;
  left:0;
}
#main {
  /* margin-top: 80px;
  height: 100%; */
  height: 100%;
  width: 100%;
  position: absolute;
  background-image: url(../assets/SmoothieBackground.jpg);
  background-repeat: no-repeat;
  background-position: 0 0;
  top: 20px;
  /* height: 1000px; */
  background-size: cover;
}
#buttons {
  margin-left: 2%;
  margin-top: 15%;
}
#buttons h2 {
  font-size: 30px;
  color: white;
}
#but2 {
  margin-top: 5%;
}
  button{
    background-color: rgb(208, 18, 18);
        color: white;
        font-size: 20px;
        padding: 12px 30px;
        min-width: 170px;
        min-height: 40px;
        margin-top: 0;
        /* display: block; */
        margin-bottom: 10px;
        margin-right: 10px;
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
