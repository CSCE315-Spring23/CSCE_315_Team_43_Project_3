<script setup>
import { reactive, ref } from 'vue';
import { useItemStore } from '@/stores/CurrentItem';
import axios from 'axios'
const itemStore = useItemStore();
    const smoothieKingIngredients = new Map([
  ['Almond Butter', 'Protein'],
  ['Almonds', 'Protein'],
  ['Apple Blueberry Juice Blend', 'Fruits and Veggies'],
  ['Apple Juice Blend', 'Fruits and Veggies'],
  ['Apple Pineapple Juice Blend', 'Fruits and Veggies'],
  ['Bananas', 'Fruits and Veggies'],
  ['Blueberries', 'Fruits and Veggies'],
  ['Carrots', 'Fruits and Veggies'],
  ['Cold Brew Coffee', 'Energy'],
  ['Dairy Whey Blend', 'Protein'],
  ['Dates', 'Fruits and Veggies'],
  ['Diet Down Enhancer', 'Enhancers'],
  ['Electrolyte Blend', 'Energy'],
  ['Energy Boost Enhancer', 'Energy'],
  ['Espresso', 'Energy'],
  ['Fiber Blend Enhancer', 'Enhancers'],
  ['FITAID + Paleo Protein Enhancer', 'Energy'],
  ['Ginger', 'Fruits and Veggies'],
  ['Gladiator Protein Chocolate', 'Protein'],
  ['Gladiator Protein Strawberry', 'Protein'],
  ['Gladiator Protein Vanilla', 'Protein'],
  ['Greek Nonfat Yogurt', 'Protein'],
  ['Gut Health Enhancer', 'Enhancers'],
  ['Hulk Blend', 'Fruits and Veggies'],
  ['Immune Support Enhancer', 'Enhancers'],
  ['Joint Health Enhancer with Collagen', 'Enhancers'],
  ['Kale', 'Fruits and Veggies'],
  ['Keto Protein Blend', 'Fruits and Veggies'],
  ['Kiwi Apple Juice Blend', 'Fruits and Veggies'],
  ['Lean1 Chocolate Protein', 'Protein'],
  ['Lean1 Vanilla Protein', 'Protein'],
  ['Mangoes', 'Fruits and Veggies'],
  ['Matcha Green Tea', 'Energy'],
  ['Metabolism Boost Enhancer', 'Energy'],
  ['Multivitamin Enhancer', 'Enhancers'],
  ['Muscle Builder Enhancer', 'Enhancers'],
  ['Orange Juice Blend', 'Fruits and Veggies'],
  ['Papaya Juice Blend', 'Fruits and Veggies'],
  ['Peanut Butter', 'Protein'],
  ['Pear Juice Blend', 'Fruits and Veggies'],
  ['Pear Passion Fruit Juice Blend', 'Fruits and Veggies'],
  ['Pineapples', 'Fruits and Veggies'],
  ['Probiotic Powder Enhancer', 'Enhancers'],
  ['Protein Blend', 'Protein'],
  ['Pumpkin', 'Fruits and Veggies'],
  ['Pure Recharge Enhancer', 'Enhancers'],
  ['Raspberries', 'Fruits and Veggies'],
  ['Spice Blend', 'Energy'],
  ['Spinach', 'Fruits and Veggies'],
  ['Strawberries', 'Fruits and Veggies'],
  ['Strawberries (sugar added)', 'Fruits and Veggies'],
  ['Sunwarrior Organic Plant-Based Protein', 'Protein'],
  ['Super Grains Enhancer', 'Enhancers'],
  ['Tart Cherries', 'Fruits and Veggies'],
  ['Whey Protein', 'Protein']
]);
const arr = ref(0);
const namee = ref("");
const ingredientCurrent = reactive([]);
async function open(n) {
  arr.value = 1;
  namee.value = n;
  ingredientCurrent.splice(0,ingredientCurrent.length);
  await axios.get('/api/allingredients')
   .then(response => {
        const stuff = response.data;
        for (let i = 0; i<stuff.length; i++){
          console.log(stuff[i] + "=>" + smoothieKingIngredients.get(stuff[i]));
          console.log(namee);
          if (itemStore.ingredients.includes(stuff[i]))
          continue;
          if (namee.value == 'Extras' && !smoothieKingIngredients.has(stuff[i])){
            ingredientCurrent.push(stuff[i]);
          }
          else if (namee.value != 'Extras' && smoothieKingIngredients.get(stuff[i]) == namee.value)
            ingredientCurrent.push(stuff[i]);
        }
    })
    .catch(error => {
        console.error(error);
    });
}
function close() {
  arr.value = 0;
}
function pick(item) {
  itemStore.pushIngredient(item);
  close();
}
</script>
<template>
  <div id="ingredientOptions" role="list">
    <div class="ingredientType" role="listbox" @click="open('Energy')" v-if="arr==0">Add Energy</div>
    <div class="ingredientType" role="listbox" @click="open('Fruits and Veggies')" v-if="arr==0">Add Fruits and Vegtables</div>
    <div class="ingredientType" role="listbox" @click="open('Enhancers')" v-if="arr==0">Add Enhacers</div>
    <div class="ingredientType" role="listbox" @click="open('Protein')" v-if="arr==0">Add Protein</div>
    <div class="ingredientType" role="listbox" @click="open('Extras')" v-if="arr==0">Add Extras</div>
  </div>
  <div id="ingredient_specifics" v-if="arr==1">
  <h3>{{ namee }}</h3>
  <div role="list" id="ingredient_list">
    <div role="button" aria-label="back button" class="buttonz" @click="close" id="back_butt">Back</div>
    <div v-for="item in ingredientCurrent" class="buttonz" role="button" :aria-label="'Pick ' + item" @click="pick(item)">{{ item }}</div>
  </div>
</div>

</template>
<style scoped>
#ingredient_specifics {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#ingredient_list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}
#back_butt {
  background-color: red;
}
.buttonz {
  padding: 6px 12px;
  margin: 8px;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  color: #fff;
  background-color: #2d6a4f;
  cursor: pointer;
}

.buttonz:hover {
  background-color: #418c6b;
}
h3 {
  font-size: 24px;
}
.buttonz:focus {
  outline: none;
  box-shadow: 0 0 0 2px #88c9a1;
}
#ingredientOptions {
  margin-left: 5px;
  margin-top: 20px;
  width: 90%;
}
  .ingredientType {
    border: 1px black solid;
    padding: 5px;
    margin-bottom: 5px;
    font-size: 20px;
    cursor: pointer;
  }
  .ingredientType:hover {
    background-color: lightseagreen;
  }
  /* .ingredientType:hover::after {
  content: "";
  border: solid black;
  border-width: 6px 0 6px 6px;
  display: inline-block;
  width: 0;
  height: 0;
  transform: rotate(90deg);
  margin-left: 5px;
} */
</style>