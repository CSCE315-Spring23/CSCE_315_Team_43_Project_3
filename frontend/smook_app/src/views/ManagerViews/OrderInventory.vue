<script setup>
import { useRouter } from 'vue-router'
// import {ref } from 'vue'
import { ref } from 'vue'
import axios from 'axios';
import SearchDropdown from "../../components/managerPanels/SearchDropdown.vue";
import Heading from '../../components/Heading.vue';
import NavBar from '../../components/managerPanels/Back.vue';

const router = useRouter()

const ingredient = ref('')
const amount = ref('')

async function login() {
    if (ingredient.value != "" && amount.value != ""){
        await axios
          .post('https://smook-app.uc.r.appspot.com/api/addOrder?invList='+ingredient.value+'&quantity='+amount.value)
          .then(response => {
          })
          .catch(error => console.log(error))
        router.replace('/manager')
    }
    else
        err.value = 'bad';
}
</script>

<template>
    <Heading true/>
    <br>
    <br>
    <br>
    <aria-label="Back" NavBar/>
    <div id="mainFormDiv" class="centered-div">
        <form @submit.prevent="login" data-testid="loginControl">
        <label for="ingredient">Ingredient</label>
        <input type="text" v-model="ingredient" class="formIn"><br>
        <label for="amount">Amount</label>
        <input type="text" v-model="amount" class="formIn"><br>
        <input type="submit" value="Add to Order" id="sub">
        <p class="error" v-show="err=='bad'">Error: You must login as type "manager" or "server"</p>
        </form>
    </div>
</template>

<style>
@import url(https://cdn.syncfusion.com/ej2/material.css);
</style>

<style scoped>
.centered-div {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.error {
    color: red;
    /* display: none;; */
}
h1 {
  font-size: 2rem;
  text-align: center;
  margin-bottom: 2rem;
}

form {
  width: 100%;
  max-width: 400px;
  padding: 2rem;
  border-radius: 10px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

label {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}

input[type="text"],
input[type="password"] {
  display: block;
  width: 100%;
  padding: 0.5rem;
  border: none;
  border-radius: 5px;
  margin-bottom: 1rem;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

#sub {
  background-color: #af4c5e;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.2s ease;
}

#sub:hover {
  background-color: #3e8e41;
}
</style>