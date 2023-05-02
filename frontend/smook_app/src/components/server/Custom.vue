<script setup>
import { useItemStore } from '@/stores/CurrentItem';
import ingredients from "../shared/ingredients.vue"
const itemStore = useItemStore();
const emit = defineEmits(['eventt','carted'])

function closeThis() {
  emit('eventt')
}
function cart() {
    emit('carted')
}
function size(s) {
        document.getElementById('small').style.backgroundColor = "#FF6B6B";
        document.getElementById('medium').style.backgroundColor = "#FF6B6B";
        document.getElementById('large').style.backgroundColor = "#FF6B6B";
        document.getElementById(s).style.backgroundColor = "#2196F3";
        itemStore.changeSize(s);
        console.log(itemStore.size);
        console.log(itemStore.price);
    }
    function remove (item) {
      itemStore.removeItem(item);
    }
</script>
<template>
    <div id="custom">
      <div class="close-button" @click="closeThis" role="button" aria-label="Close">
        X
      </div>
      <h2>{{ itemStore.name }}</h2>
      <div id="addOn">
        <div id="sizeButtons">
            <h4>Size:</h4>
            <button @click="size('small')" id="small">Small</button>
            <button @click="size('medium')" id="medium">Medium</button>
            <button @click="size('large')" id="large">Large</button>
        </div>
        <div id="curr">
          <h4>Current Ingredients:</h4>
          <div class="currIng" v-for="ing in itemStore.ingredients">{{ ing }}<span @click="remove(ing)" class="delete">X</span></div>
        </div>
        <div id="future">
          <h4>Customizations:</h4>
          <ingredients />
        </div>
        </div>
        <div id="sub" @click="cart()">Add-<span>{{ itemStore.getPrice }}</span></div>
    </div>
  </template>
  
  <style scoped>
    #custom {
      height: 79%;
      width: 91%;
      margin-left: 3%;
      background-color: white;
      margin-top: 100px;
      position: relative;
      border: 2px solid black;
    }
    h2 {
        text-align: center;
        font-size: 35px;
    }
    h4 {
        font-size: 28px;
        text-decoration: underline;
    }
    .close-button {
      position: absolute;
      top: -10px;
      right: -10px;
      width: 40px;
      height: 40px;
      border-radius: 50%;
      background-color: #e74c3c;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      font-size: 30px;
      color: white;
      z-index: 1000;
    }
    #addOn {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    flex: 1;
    }
    #addOn div {
      flex-grow: 7;
      max-width: 50%;
    }
    #sizeButtons {
        margin-left: 20px;
        flex-grow: 1 !important;
    }
    #sizeButtons button {
        display: block;
        margin-top: 20px;
        background-color: #FF6B6B; 
        color: #FFFFFF; 
        border-radius: 50px; 
        padding: 25px 40px; 
        font-size: 20px; 
        font-weight: bold; 
        width: 300px;
        border: none; 
        outline: none;
        cursor: pointer;
        transition: background-color;
    }
    #sizeButtons button:hover {
  background-color: #f32828;
  color: #FFFFFF;
  transform: scale(1.1);
  transition: all 0.2s ease-in-out;
}
#sub {
    position: absolute;
    bottom: 1%;
    right: 1%;
    padding: 12px;
    font-size: 20px;
    border: 1px solid black;
    background-color: aqua;
    cursor: pointer;
}
.currIng {
  font-size: 20px;
  border: 1px black dotted;
  padding: 5px;
  margin-top: 7px;
  width: 90%;
  max-width: 100% !important;
}
#curr {
  flex-grow: 2 !important;
  border-right: 3px black solid;
  overflow: auto;
}

#future {
  flex-grow: 7;
  max-width: 50%;
  overflow: auto;
}
.delete {
  color: red;
  cursor: pointer;
  position: absolute;
  right: 7%;
}
#future h4 {
  text-align: center;
}
  </style>