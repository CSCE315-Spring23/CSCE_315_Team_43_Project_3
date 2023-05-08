<script setup>
import Heading from '../components/Heading.vue';
import Ingredients from '../components/shared/Ingredients.vue'
import cartButton from '../components/customer/cartButton.vue';
import SmoothieImg from '../components/customer/SmoothieImg.vue';
import Size from '../components/customer/Size.vue';
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
          <div id="editPanel">
            <div class="scrollable-content">
            <div id="size">
              <h3 class="editHeader">1. Size</h3>
              <Size />
            </div>
            <div id="currentRecipe" role="section" aria-label="Lists current composition of smoothie">
              <h3 class="editHeader">2. Current Recipe</h3>
              <div class="centeringCurr">
                <div class="currIng" v-for="ing in itemStore.ingredients" role="listitem" >{{ ing }}<span @click="itemStore.removeItem(ing)" class="delete" role="button" aria-label="Button removes ingredient from smoothie">X</span></div>
              </div>
            </div>
            <div id="addOns">
              <h3 class="editHeader">3. Add Ons (+$.99 per)</h3>
              <Ingredients />
            </div>
          </div>
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
h3 {
  font-size: 30px;
  text-decoration: underline;
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
#titlePanel {
  flex-grow: 1;
}
#titlePanel h1 {
  text-align: center;
  font-size: 40px;
  margin-bottom: 30px;
}
#mainContent {
  padding-left: 8%;
  padding-right: 8%;
  top: 63px;
  justify-content: space-between;
  padding-top: 40px;
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  flex-direction: row;
  flex: 1;
}
#editPanel {
  flex-grow: 5;
  margin-left: 70px;
  max-width: 703px;
  min-width: 630px;
  height: 90vh; /* set a fixed height */
  overflow-y: scroll; /* enable scrolling */
}
/* .scrollable-content {
  overflow-y: scroll;
} */
.currIng {
  font-size: 20px;
  border: 1px black dotted;
  padding: 5px;
  margin-top: 7px;
  width: 74%;
  max-width: 100% !important;
}
.currIng {
  position: relative;
  padding-right: 20px;
}

.currIng .delete {
  position: absolute;
  top: 20%;
  right: 5%;
}

.scrollable-content {
  position: relative;
}

#editPanel {
  flex-grow: 5;
  margin-left: 70px;
  max-width: 703px;
  min-width: 630px;
  height: 80vh;
  overflow: scroll;
}
.delete {
  color: red;
  cursor: pointer;
  position:sticky;
}
/* .scrollable-content {
  overflow-y: scroll;
} */
.scrollable-content  > div {
  margin-bottom: 15px;
}
.centeringCurr {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>