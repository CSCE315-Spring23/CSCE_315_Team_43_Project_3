<script setup>
import Heading from '../components/Heading.vue';
import { reactive, ref } from 'vue';
import Transaction from '../components/server/Transaction.vue';
import Custom from '../components/server/Custom.vue';
import axios from 'axios';
import { useItemStore } from '@/stores/CurrentItem';
import {useCartStore} from '@/stores/CartStore';
const itemStore = useItemStore();
const cartStore = useCartStore();
const itemTypes = reactive([]);
const activeOne = ref(0);
const smoothiesDisplayed = reactive([]);
const addOn = ref(false)
const opac = ref(1.0);
async function getCatagories() {
  try {
    const response = await axios.get('https://smook-app.uc.r.appspot.com/api/category');
    const categories = response.data;
    for (let i = 0; i < categories.length; i++) {
      if (categories[categories.length - i - 1] == 'Other' || categories[categories.length - i - 1] == 'Enhance') {
        continue;
      }
      itemTypes.push({ category: categories[categories.length - i - 1], active: 'notSelected' });
    }
  } catch (error) {
    console.error(error);
  }
  itemTypes[0].active = 'selected';
  switcher(0);
}
async function getSmoothies(cat) {
  try {
    const response = await axios.get('https://smook-app.uc.r.appspot.com/api/items', { params: { category: cat } });
    const smoothies = response.data;
    for (let i = 0; i < smoothies.length; i++) {
      smoothiesDisplayed.push(smoothies[i]);
    }
  } catch (error) {
    console.error(error);
  }
  smoothiesDisplayed.sort((a, b) => {
  if (a.startsWith('The ')) {
    a = a.substr(4);
  }
  if (b.startsWith('The ')) {
    b = b.substr(4);
  }
  return a.localeCompare(b);
});
}
function switcher(i) {
  itemTypes[activeOne.value].active = 'notSelected';
  itemTypes[i].active = 'selected';
  smoothiesDisplayed.splice(0, smoothiesDisplayed.length);
  getSmoothies(itemTypes[i].category);
  activeOne.value = i;
}
function switchRight(i) {
  if (i < itemTypes.length - 1) {
    switcher(i + 1);
  }
}
function switchLeft(i) {
  if (i > 0) {
    switcher(i - 1);
  }
}
function clicked(smoothie) {
  opac.value = .2;
  itemStore.changeName(smoothie);
  addOn.value = true;
}
function close() {
  opac.value = 1;
  addOn.value = false;
  itemStore.$reset();
}
function cart() {
  if(itemStore.size =='none')
  return
  let smoothie = {
    name: itemStore.name,
    size: itemStore.size,
    ingredients: itemStore.ingredients,
    price: itemStore.price
}
cartStore.addItem(smoothie);
close();
console.log(cartStore.cart.length);
}
getCatagories();
</script>
<template>
  <Heading />
  <main>
    <div id="main" :style="{opacity: opac}">
      <div id="orderPanel">
        <div id="typePicker">
          <div
            v-for="(itemType, i) in itemTypes"
            :key="'type-' + i"
            @click="switcher(i)"
            :class="{'selectedd': itemType.active === 'selected'}"
            tabindex="0"
            @keydown.enter="switcher(i)"
            @keydown.arrowright.prevent="switchRight(i)"
            @keydown.arrowleft.prevent="switchLeft(i)"
            role="button"
            aria-label="Select smoothie type"
          >
            {{ itemType.category }}
          </div>
        </div>
        <div id="smoothiePicker">
          <div
            v-for="(smoothie, i) in smoothiesDisplayed"
            :key="'smoothie-' + i"
            class="smook"
            @click="clicked(smoothie)"
            role="button"
            aria-label="Select smoothie"
          >
            {{ smoothie }}
          </div>
        </div>
        <!-- <h3>OrderPanel</h3> -->
      </div>
      <Transaction/>
    </div>
    <Custom 
    v-if="addOn==true"
    @eventt="close"
    @carted="cart"
    />
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
  width: 74%;
  height: 100%;
  border-right: 4px black solid;
}
#cartViewServer {
    width: 400px;
    background-color: green;
}
#typePicker {
  display: flex;
  justify-content: center;
  flex: 1;
  border-bottom: 2px solid #FF6B6B;
}
#typePicker div {
  flex-grow: 1;
  text-align: center;
  cursor: pointer;
  font-size: 23px;
  border-right: 2px dashed #FF6B6B;
  padding: 17px 20px;
  transition: background-color .5s;
}
#typePicker div:hover {
  background-color: rgb(199, 225, 241);
}
#typePicker div:last-of-type {
  border: 0;
}
.selectedd  {
  background-color: lightskyblue;
  text-decoration: underline;
}
#smoothiePicker {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  height: 73%;
}
.smook {
  border: 3px black solid;
  border-radius: 20px;
  padding: 20px;
  font-size: 21px;
  margin: 5px;
  cursor: pointer;
}
.smook:hover {
  background-color: #FF6B6B;
}
#orderPanel h3 {
  bottom: 0;
  font-size: 30px;
  text-align: center;
  margin-top: 20px
}
</style>