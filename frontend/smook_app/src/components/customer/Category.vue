<script setup>

import { reactive } from 'vue'
import Smoothie from './Smoothie.vue'
defineProps({
  catName: {
    type: String,
    required: true
  }
})
let drink1 = {
    name: 'Espreso Coffee Vanilla',
    image: 'imgs/smoothie1.png',
    ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
}
let drink2 = {
    name: 'Espreso Coffee chocolate',
    image: 'imgs/smoothie2.png',
    ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
}
let drink3 = {
    name: 'Cool bean Vanilla',
    image: 'imgs/smoothie1.png',
    ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
}
let drink4 = {
    name: 'Veryy bean Vanilla',
    image: 'imgs/smoothie1.png',
    ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
}
let drink5 = {
    name: 'Veryy Yikes',
    image: 'imgs/smoothie1.png',
    ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
}

let smoothies = reactive([])
smoothies.push(drink1)
smoothies.push(drink2)
smoothies.push(drink3)
smoothies.push(drink4)
smoothies.push(drink5)
smoothies.push(drink1)
smoothies.push(drink2)
smoothies.push(drink3)
smoothies.push(drink4)
smoothies.push(drink5)
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
            :itemN="smoothie.name"
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