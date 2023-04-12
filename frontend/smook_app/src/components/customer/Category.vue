<script setup>

import { reactive } from 'vue'
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

let smoothies = reactive([])
smoothies.push(drink1)
smoothies.push(drink2)
smoothies.push(drink3)
</script>
<template>
    <div id="cat">
        <div class="divider">
            <h2>{{ catName }}</h2>
        </div>
        <div class="items">
            <div v-for="smoothie in smoothies" :key="smoothie.name">
                <h3>{{ smoothie.name }}</h3>
                <!-- <img :src="smoothie.image" alt="Ayo"> -->
            </div>
        </div>
    </div>
</template>
<style scoped>
    .divider {
        width: 100%;
        background-color: lightcoral;
    }
    .divider h2 {
        text-align: center;
    }
</style>