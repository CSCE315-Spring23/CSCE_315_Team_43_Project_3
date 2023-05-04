<script setup>
import { useRouter } from 'vue-router'
// import {ref } from 'vue'
import { ref } from 'vue'
import axios from 'axios';
import SearchDropdown from "../../components/managerPanels/SearchDropdown.vue";
import Heading from '../../components/Heading.vue';
import NavBar from '../../components/managerPanels/Back.vue';
import VuetifyDatatable from "../../components/managerPanels/ExcessTable.vue";
import { reactive } from 'vue'

const router = useRouter()

const start_date = ref('2023-05-1 0:0:0.0')
const end_date = ref('2023-05-2 0:0:0.0')

let posts = reactive([]);
async function login() {
    console.log('https://smook-app.uc.r.appspot.com/api/ExcessReport?startTime='+start_date.value+'&endTime='+end_date.value);
    if (start_date.value != "" && end_date.value != ""){
        await axios
          .get('https://smook-app.uc.r.appspot.com/api/ExcessReport?startTime='+start_date.value+'&endTime='+end_date.value)
          .then(response => {
            const report = response.data;
            if (response == null)
              alert("Hello! I am an alert box!!");
            for (let i = 0; i< report.length; i++){
              posts.push(report[report.length-i-1]);
            }
          })
          .catch(error => console.log(error))
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
    <NavBar/>
    <div id="mainFormDiv" class="centered-div">
        <form @submit.prevent="login" data-testid="loginControl">
        <label for="start_date">Start Date</label>
        <input type="text" v-model="start_date" class="formIn"><br>
        <label for="end_date">End Date</label>
        <input type="text" v-model="end_date" class="formIn"><br>
        <input type="submit" value="Generate" id="sub">
        <p class="error" v-show="err=='bad'">Error: Dates are not valid.</p>
        </form>
    </div>
    <div>
        <VuetifyDatatable :posts="posts" />
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