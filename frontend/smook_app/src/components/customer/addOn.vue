<script setup>
import { useItemStore } from '@/stores/CurrentItem';
import { ref } from 'vue';
        const itemStore = useItemStore();
    function size(s) {
        itemStore.changeSize(s);
        console.log(itemStore.size);
        console.log(itemStore.price);
    }
</script>
<template>
    <div id="main3">
    <div id="sizeButtons">
            <button @click="size('small')" id="small" v-if="itemStore.size != 'small'">Small</button>
            <button @click="size('small')" id="small" class="selected" v-if="itemStore.size == 'small'">Small</button>
            <button @click="size('medium')" id="medium" v-if="itemStore.size != 'medium'">Medium</button>
            <button @click="size('medium')" id="medium"  class="selected" v-if="itemStore.size == 'medium'">Medium</button>
            <button @click="size('large')" id="large" v-if="itemStore.size != 'large'">Large</button>
            <button @click="size('large')" id="large" class="selected" v-if="itemStore.size == 'large'">Large</button>
    </div>
        <div class="centerer">
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
        position: absolute;
        margin-top: 5%;
        display: flex;
        flex-direction: column;
        width: 180px;
    }
    .centerer {
        display: flex;
        justify-content: center;
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
        margin-bottom: 10px;
    }
    #sizeButtons button:hover {
  background-color: #f32828;
  color: #FFFFFF;
  transform: scale(1.1);
  transition: all 0.2s ease-in-out;
}
.selected {
    background-color: #2196F3 !important;
}
</style>