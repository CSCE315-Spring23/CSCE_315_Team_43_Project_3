<script setup>

import { useRouter } from 'vue-router'
// import {ref } from 'vue'

import { ref } from 'vue'
//import { routerKey } from 'vue-router';
// defineProps({
//   log: {
//     type: Boolean,
//     required: true
//   }
// })
const router = useRouter()

function login() {
    router.push('/login');
}
function gohome() {
    if (router.currentRoute.value.name=="home"){
        location.reload();
    }
    else
    router.push('/');
}
</script>
<template>
    <div id="head">
        <button id="homeButton" @click="gohome()">
            <img src="..\assets\smoothie-king-logo.svg" alt="Smoothie King Logo" height="64">
        </button>
        <button id="signin" @click="login()" v-if="!log">Sign in</button>
    </div>
    <!-- <div id="spacer">

    </div> -->
</template>
<style scoped>
    #head {
        width: 100%;
        position: fixed;
        height: 75px;
        /* background-color: blue; */
        top: 0px;
        left: 0px;
        border-bottom: 4px grey ridge;
        z-index: 999;
        background-color: white;
    }
    button {
        background-color: white;
        color: white;
        border: none;
        margin-left: 20px;
        cursor: pointer;
    }
    #signin {
        background-color: black;
        color: white;
        font-size: 22px;
        padding: 10px 30px;
        margin-top: 0;
        position: absolute;
        top:12%;
        right: 2%;
        border-radius: 10px;
        border: black 5px solid;
        box-sizing: border-box;
        cursor: pointer;
    }
    #signin:hover {
        background-color: white;
        color: black;
    }
    /* #spacer {
        height: 72px;
    } */
</style>