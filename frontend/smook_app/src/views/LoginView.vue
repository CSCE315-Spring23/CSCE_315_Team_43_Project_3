
<script setup>

import { useRouter } from 'vue-router'
// import {ref } from 'vue'
import Heading from '../components/Heading.vue';
import { ref } from 'vue'
import axios from 'axios';

const router = useRouter()

const user = ref('')

const pass = ref('')

const err = ref('')

const loginVerify = ref('')

// const instance = axios.create({
//   baseURL: 'http://localhost:8080',
//   timeout: 1000
// });

async function login() {
    console.log(user.value);
    if (user.value == "server")
        router.replace('/server')
    else if (user.value == "manager")
        router.replace('/manager')
    else if (user.value != "" && pass.value != "")
        axios
          .get('http://localhost:8080' + '/login?username=' + user.value + '&password=' + pass.value)
          .then(response => {
            if(response.data == 1) {router.replace('/manager');}
            else if(response.data == 2) {router.replace('/employee');}
            else {err.value = 'bad';}
          })
          .catch(error => console.log(error))
    else
        err.value = 'bad';
    console.log(err);
}

async function oauthLogin() {
  console.log('Clicked!');
  // TODO: route back?
}

</script>
<template>
    <Heading true/>
    <div id="mainFormDiv" class="centered-div">
        <!-- <h1>Login</h1> -->
        <form @submit.prevent="login" data-testid="loginControl">
        <label for="username">Username</label>
        <input type="text" v-model="user" class="formIn"><br>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" class="formIn" v-model="pass"><br>
        <input type="submit" value="Login" id="sub">
        <a id="google-login" href="http://localhost:8080/oauth2/authorization/google">
          <b-button @click="oauthLogin">Login with Google</b-button>
        </a>
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
#google-login {
  padding-left: 10px;
}
</style>