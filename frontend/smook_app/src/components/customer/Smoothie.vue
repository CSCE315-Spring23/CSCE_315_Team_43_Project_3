<script setup>
import { reactive } from 'vue'
defineProps({
  itemN: String
})
console.log("Ayo");
const drinks = reactive(
    new Map(
        [
            ['Espreso Coffee Vanilla',['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']],
            ['Espreso Coffee chocolate',['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']],
            ['Cool bean Vanilla',['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']]
        ]
    )
)

//const ingredients = drinks.get(itemN.value);
function smoothieClick() {

}

</script>
<template>
    <div id="smoothie" @click="smoothieClick">
      <div class="smoothieItem">
        <img src="./imgs/smoothie1.png" alt="Ayo">
        <h4>{{ itemN }}</h4>
        <!-- <ul v-if="ingredients.length>0">
          <li v-for="thing in ingredients" :key="thing">{{ thing }}</li>
        </ul> -->
      </div>
    </div>
</template>
<style scoped>
  #smoothie {
    /* flex: 1; */
    /* min-width: 270px;
    max-width: 270px; */
    min-width: calc(20% - 10px); /* set a fixed width for each item */
    margin-bottom: 20px; /* set a margin between the rows */
    display: flex;
    justify-content: center;
    align-items: center;
    border: black solid 1px;
    margin-left: 5px;
    cursor: pointer;
  }
  .smoothieItem {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .smoothieItem h4 {
    font-size: 20px;
    text-align: center;
  }
  .smoothieItem img {
    margin-top: 6px;
  }
  #smoothie:hover {
    background-color: rgb(105, 141, 207);
  }
</style>