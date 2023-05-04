<script setup>
import { useRouter } from 'vue-router'
// import {ref } from 'vue'
import { ref } from 'vue'
import axios from 'axios';
import Heading from '../../components/Heading.vue';
import NavBar from '../../components/managerPanels/Back.vue';

const router = useRouter()

const id = ref('')
const name = ref('')
const type = ref('')
const price = ref('')
const ingredientAmount = ref('')
const ingredientIds = ref('')
const ingredientQuantity = ref('')

const err = ref('')

async function login() {
    console.log(name.value);
    if (name.value != "" && type.value != "" && price.value != "" && ingredientAmount.value != ""  && ingredientIds.value != "" && ingredientQuantity.value != ""){
        await axios
          .post('https://smook-app.uc.r.appspot.com/api/addItem?name='+name.value+'&type='+type.value+'&price='+price.value+'&ingredientAmount='+ingredientAmount.value+'&ingredientIds='+ingredientIds.value+'&ingredientQuantity='+ingredientQuantity.value)
          .then(response => {
          })
          .catch(error => console.log(error))
        router.replace('/manager')
    }
    else
        err.value = 'bad';
    console.log(err);
}
</script>

<template>
    <Heading true/>
    <br>
    <br>
    <br>
    <aria-label="Back" NavBar/>
    <br>
    <br>
    <br>
    <div id="mainFormDiv" class="centered-div">
        <form @submit.prevent="login" data-testid="loginControl">
        <label for="id">Menu ID</label>
        <input type="text" v-model="id" class="formIn"><br>
        <label for="name">Name</label>
        <input type="text" v-model="name" class="formIn"><br>
        <label for="type">Type</label>
        <input type="text" v-model="type" class="formIn"><br>
        <label for="price">Price</label>
        <input type="text" v-model="price" class="formIn"><br>
        <label for="ingredientAmount">Ingredient Amount</label>
        <input type="text" v-model="ingredientAmount" class="formIn"><br>
        <label for="ingredientIds">Ingredient IDs</label>
        <input type="text" v-model="ingredientIds" class="formIn"><br>
        <label for="ingredientQuantity">Ingredient Quantities</label>
        <input type="text" v-model="ingredientQuantity" class="formIn"><br>
        <input type="submit" value="Edit Menu" id="sub">
        <p class="error" v-show="err=='bad'">Error: You must login as type "manager" or "server"</p>
        </form>
    </div>
</template>

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