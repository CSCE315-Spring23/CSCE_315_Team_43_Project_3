<script setup>
   import { reactive } from 'vue'
   import { useItemStore } from '@/stores/CurrentItem'
   import axios from 'axios'
   const ingredients = reactive([])
   const itemStore = useItemStore();
   axios.get('https://smook-app.uc.r.appspot.com/api/allingredients')
   .then(response => {
        const stuff = response.data;
        for (let i = 0; i<stuff.length; i++){
            if (stuff[i]!='test3')
            ingredients.push(stuff[stuff.length-i-1]);
        }
    })
    .catch(error => {
        console.error(error);
    });
//    const props = defineProps(['currents']);
   function remover(item) {
    itemStore.removeItem(item);
   }
   function adder(item) {
    itemStore.pushIngredient(item);
   }
</script>
<template>
    <div id="control">
        <div id="curr">
            <span v-for="ing in itemStore.ingredients">{{ ing }} <button @click="remover(ing)">X</button></span>
        </div>
        <div id="adds">
            <span v-for="ing in ingredients" @click="adder(ing)" id="chooser">{{ ing }}</span>
        </div>
    </div>
</template>
<style scoped>
    #control{
        top: 600px;
        position: absolute;
        display: flex;
    }
    span {
        display: block;
    }
    #curr {
        border: 2px black solid;
    }
    #chooser 
    {
        cursor: pointer;
    }
</style>