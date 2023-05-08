<script setup>
import Heading from '../components/Heading.vue';
import { reactive } from 'vue';
import Category from '../components/customer/Category.vue'
import cartButton from '../components/customer/cartButton.vue'
import axios from 'axios';
const itemTypes = reactive([]);
async function getCatagories() {
  await axios.get('https://smook-app.uc.r.appspot.com/api/category', {
})
  .then(response => {
    const categories = response.data;
    for (let i = 0; i<categories.length; i++){
      if (categories[categories.length-i-1] == "Other")
      continue
      if (categories[categories.length-i-1] == "Enhance")
      continue
      itemTypes.push(categories[categories.length-i-1]);
    }
  })
  .catch(error => {
    console.error(error);
  });
}
getCatagories();
</script>

<template>
      <Heading />
  <main>
    <div id="main">
        <header>
            <h1>Order Menu</h1>
        </header>
        <div id="orderPanel">
            <Category 
                v-for="item in itemTypes"
                :key="item"
                :catName="item"
            />
        </div>
    </div>
  </main>
  <cartButton />
</template>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Righteous&family=Ubuntu&display=swap');
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
#main header {
    height: 25%;
    background-color: lightblue;
    display: flex;
    justify-content: center;
    align-items: center;
    /* background-image: url(../assets/employee_login.jpg);
    background-repeat: repeat-x; */
}
#main header h1 {
    font-size: 100px;
    text-align: center;
    font-family: 'Righteous', cursive;
}
#orderPanel {
    padding: 3%;
    padding-top: 5px;
    padding-bottom: 0;
    margin-bottom: 30px;
}
</style>
