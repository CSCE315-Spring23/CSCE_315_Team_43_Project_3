<script setup>
import { useItemStore } from '@/stores/CurrentItem';
import { ref } from 'vue';
const itemStore = useItemStore();
const emit = defineEmits(['scrollNow']);
    function size(s) {
        emit('scrollNow');
        document.getElementById('small').style.backgroundColor = "#FF6B6B";
        document.getElementById('medium').style.backgroundColor = "#FF6B6B";
        document.getElementById('large').style.backgroundColor = "#FF6B6B";
        document.getElementById(s).style.backgroundColor = "#2196F3";
        itemStore.changeSize(s);
        console.log(itemStore.size);
        console.log(itemStore.price);
    }
</script>
<template>
    <div id="main3">
    <div id="img">
        <img src="./imgs/smoothie1.png" alt="Ayo">
    </div>
        <div class="centerer">
        <div id="sizeButtons">
            <button @click="size('small')" id="small">Small</button>
            <button @click="size('medium')" id="medium">Medium</button>
            <button @click="size('large')" id="large">Large</button>
        </div>
        </div>
    </div>
</template>
<style scoped>
    #main3 {
        margin-top: 32px;
    }
    #id {
        width: 100%;
    }
    img {
        display: block;
        margin: 0 auto;
    }
    #sizeButtons {
        margin-top: 5%;
    }
    .centerer {
        display: flex;
        justify-content: center;
        margin-top: 50px;
    }
    #sizeButtons button {
        margin-left: 12px;
        background-color: #FF6B6B; 
        color: #FFFFFF; 
        border-radius: 50px; 
        padding: 15px 40px; 
        font-size: 20px; 
        font-weight: bold; 
        border: none; 
        outline: none;
        cursor: pointer;
        transition: background-color;
    }
    #sizeButtons button:first-of-type {
        margin-left: 0;
    }
    #sizeButtons button:hover {
  background-color: #f32828;
  color: #FFFFFF;
  transform: scale(1.1);
  transition: all 0.2s ease-in-out;
}
</style>