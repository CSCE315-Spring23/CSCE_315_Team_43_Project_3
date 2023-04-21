<script setup>
import {RouterView } from 'vue-router'
function httpGet(url) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}
let apikey = import.meta.env.VITE_OPENWEATHERMAP_API_KEY;
let resp = httpGet(`https://api.openweathermap.org/data/2.5/forecast?lat=30.6212&lon=-96.3404&appid=${apikey}&units=imperial&cnt=1`);
const weather = JSON.parse(resp);
let forecast = weather.list[0];
console.log(`${forecast.weather[0].description}, ${forecast.main.temp}°`);
</script>

<template>
  <!-- <header> -->
    <!-- <img alt="Vue logo" class="logo" src="@/assets/smookLogo.svg" width="125" height="125" /> -->

    <!-- <div class="wrapper">
      <HelloWorld msg="You did it!" />
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/customer">Customer</RouterLink>
        <RouterLink to="/server">Server</RouterLink>
        <RouterLink to="/manager">Manager</RouterLink>
        <RouterLink to="/menuBoard">Menu Board</RouterLink>
      </nav>
    </div>
  </header> -->
  <RouterView />
</template>

<style scoped>
/* header {
  /* line-height: 1.5;
  max-height: 100vh;
} */

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 22px;
  text-align: center;
  /*margin-top: 2rem;*/
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

/* @media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}*/
</style>
