<script setup>

import { reactive } from 'vue'
import Smoothie from './Smoothie.vue'
import axios from 'axios'
const props = defineProps({
  catName: {
    type: String,
    required: true
  }
})

const smoothies = reactive([]);
async function getSmoothies() {
    axios.get('/api/items', { params: { category: props.catName } })
  .then(response => {
    console.log("Response" + response.data);
    const drinks = response.data;
    for (let i = 0; i<drinks.length; i++){
        //console.log(drinks[drinks.length-i-1]);
        smoothies.push(drinks[drinks.length-i-1]);
    }
    //console.log(categories); // Prints the array of strings to the console
  })
  .catch(error => {
    console.error(error);
  });
}

getSmoothies();
</script>
<template>
    <div id="cat">
        <div class="divider">
            <h2>{{ catName }}</h2>
        </div>
        <div class="items">
            <!-- <div v-for="smoothie in smoothies" :key="smoothie.name">
                <h3>{{ smoothie.name }}</h3>
                <img :src="smoothie.image" alt="Ayo">
            </div> -->
            <Smoothie
            v-for="smoothie in smoothies"
            :key="smoothie"
            :item="smoothie"
            />
        </div>
    </div>
</template>
<style scoped>
    .divider {
        width: 100%;
        background-color: lightcoral;
        margin-top: 12px;
        margin-bottom: 12px;
    }
    .divider:first-of-type {
        margin-top: 0;
    }
    .divider h2 {
        text-align: center;
    }
    .items {
        display: flex;
        /* flex-wrap: wrap; */
        /* justify-content: space-between; */
        overflow: scroll;
    }

</style>