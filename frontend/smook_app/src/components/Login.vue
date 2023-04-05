<script setup>
import LoginTitle from './LoginTitle.vue';


import { RouterLink, RouterView } from 'vue-router'

function test(){
    console.log("Submitted");
}
</script>
<template>
    <LoginTitle msg="Smoothie King POS!" />
    <div class="login">
        <div>
    <form @submit.prevent="test()">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password"><br>
        <input type="submit" value="Login" id="sub">
    </form>
    <RouterLink to="/customer">Place Order</RouterLink><br>
    <RouterLink to="/menuBoard">Menu Board</RouterLink>
    </div>
</div>
</template>
<style scoped>
    .login {
        /* position: absolute; */
        display: flex; 
        justify-content: center; 
        align-items: center;
    }
    .login div {
        margin-top: 30px;
    }
    #sub {
        width: 100%;
        background-color: rgb(2, 174, 253);
        border: none;
        border-radius: 10px;
        height: 40px;
        margin-top: 30px;
        font-family: Inter, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
        font-size: 24px;
    }
</style>