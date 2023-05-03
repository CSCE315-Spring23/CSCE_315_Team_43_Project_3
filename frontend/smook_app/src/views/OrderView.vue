<script setup>
import Heading from '../components/Heading.vue';
import Size from '../components/customer/Size.vue'
import AddOn from '../components/customer/addOn.vue'
import cartButton from '../components/customer/cartButton.vue'
import SmoothieImg from '../components/customer/SmoothieImg.vue';
import { RouterLink, useRouter } from 'vue-router';
import { useItemStore } from '@/stores/CurrentItem';
import {useCartStore} from '@/stores/CartStore';
import { ref } from 'vue';
const router = useRouter();

const itemStore = useItemStore();
const cartStore = useCartStore();
const screen1 = ref(true);

// console.log("Test:" + itemStore.getPrice())
function back() {
    router.back();
}
function cart() {
  let smoothie = {
    name: itemStore.name,
    size: itemStore.size,
    ingredients: itemStore.ingredients,
    price: itemStore.price
}
cartStore.addItem(smoothie);
itemStore.$reset();
router.back();
}
function scroll(){
  document.getElementById('app').classList.add('scroll-to-top');
    setTimeout(() => {
      document.getElementById('app').classList.remove('scroll-to-top');
      window.scrollTo(0, 0);
    }, 1000);
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
            <button id="addToCart2" v-if="itemStore.size != 'none'" @click="cart">Add to Cart</button>
          </div>
        </div>
        <div id="mainContent">
        <div id="titlePanel">
          <h1>{{ itemStore.name }}</h1>
          <SmoothieImg />
        </div>
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
#itemHeading {
  display: flex;
  justify-content: space-between;
  margin: 10px;
  position: fixed;
  left: 0;
  width: 100%;
  z-index: 1000;
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
/* #titlePanel {
  top: 60px;
  width: 45%;
  z-index: -1;
} */
#titlePanel h1 {
  text-align: center;
  font-size: 40px;
  margin-bottom: 30px;
}
#mainContent {
  padding-left: 15%;
  padding-right: 15%;
  top: 63px;
}
</style>