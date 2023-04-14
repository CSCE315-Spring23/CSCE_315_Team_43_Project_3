<script setup>
import Heading from '../components/Heading.vue';

//import { useRouter } from 'vue-router'
import { reactive } from 'vue';
import Category from '../components/customer/Category.vue'
import Smoothie from '../components/customer/Smoothie.vue'
//const router = useRouter();

const itemTypes = reactive(['Feel Energized', 'Get Fit','Manage Weight','Have Fun']);

// let drink1 = {
//     name: 'Espreso Coffee Vanilla',
//     image: 'smoothie1.png',
//     ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
// }
// let drink2 = {
//     name: 'Espreso Coffee chocolate',
//     image: 'smoothie2.png',
//     ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
// }
// let drink3 = {
//     name: 'Cool bean Vanilla',
//     image: 'smoothie1.png',
//     ingredients: ['chocolate','protein powder', 'blueberries', 'whey powder', 'peanut butter']
// }

// let smoothies = reactive([])
// smoothies.push(drink1)
// smoothies.push(drink2)
// smoothies.push(drink3)
</script>

<template>
      <Heading />
  <main>
    <div id="main">
        <header>
            <h1>Smoothie Time!!!</h1>
        </header>
        <div id="orderPanel">
        <!-- <div v-for="smoothie in smoothies" :key="smoothie.name">
            <h3>{{ smoothie.name }}</h3>
        </div> -->
            <Category 
                v-for="item in itemTypes"
                :key="item"
                :catName="item"
            />
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
  height: 89%;
  width: 100%;
  position: absolute;
  top: 75px;
  /* height: 1000px; */
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
    padding: 5%;
    padding-top: 5px;
    padding-bottom: 0;
    margin-bottom: 30px;
}
</style>
