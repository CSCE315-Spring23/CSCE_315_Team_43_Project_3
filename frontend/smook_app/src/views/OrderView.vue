<script setup>
import Heading from '../components/Heading.vue';
import Size from '../components/customer/Size.vue'
import AddOn from '../components/customer/addOn.vue'

import { RouterLink, useRouter } from 'vue-router';
import { useItemStore } from '@/stores/CurrentItem';
import { ref } from 'vue';
const router = useRouter();

const itemStore = useItemStore();
const screen1 = ref(true);

// console.log("Test:" + itemStore.getPrice())
function back() {
    router.back();
}
</script>

<template>
      <Heading />
  <main>
    <div id="main">
        <div id="itemHeading">
            <button id="back" @click="back()">&lt; Go Back</button>
            <div>
            <div id="price">
                <h4><span v-if="itemStore.price!=0.0">{{itemStore.getPrice}}</span><span v-if="itemStore.price==0.0">$-</span></h4>
            </div>
            <button id="addToCart" v-if="itemStore.size == 'none'">Add to Cart</button>
            <button id="addToCart2" v-if="itemStore.size != 'none'">Add to Cart</button>
          </div>
        </div>
        <div id="titlePanel">
          <h1>{{ itemStore.name }}</h1>
        </div>
        <div id="orderingPanels">
        <Size v-if="itemStore.size == 'none'"/>
        <AddOn v-if="itemStore.size != 'none'"/>
        </div>
    </div>
  </main>
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
  /* margin-top: 80px;
  height: 100%; */
  height: 89%;
  width: 100%;
  position: absolute;
  top: 75px;
  /* height: 1000px; */
}
#itemHeading {
  display: flex;
  justify-content: space-between;
  margin: 10px;
}
#itemHeading div {
  display: flex;
  margin-right: 12px;
}
#back {
  background-color: white;
  border: 1px solid black;
  padding-left: 9px;
  padding-right: 9px;
  border-radius: 9px;
  /* text-decoration: underline black; */
  font-style: bold;
  cursor: pointer;
}
#back:hover {
  background-color: lightblue;
}
#addToCart {
  border-radius: 0;
  background-color: grey;
  border: solid #800000 1px;
  color: white;
  cursor: pointer;
  padding: 10px;
}
#addToCart2 {
  border-radius: 0;
  background-color: #2196F3; ;
  border: 0;
  color: white;
  cursor: pointer;
  padding: 10px;
}
#addToCart2:hover {
  background-color: #71bbf8;
}
#price {
  border: 3px black solid;
  border-radius: 20px;
  display: flex;
  align-items: center;
  padding: 10px;
}
#titlePanel h1 {
  text-align: center;
}
</style>