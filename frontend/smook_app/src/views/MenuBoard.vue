<script setup>
import Heading from '../components/Heading.vue';
import SmookControllerApi from '../classes/api/SmookControllerApi';
import { ref, onMounted, onUnmounted } from 'vue';
const apikey = import.meta.env.VITE_OPENWEATHERMAP_API_KEY;
const weatherUrl = `https://api.openweathermap.org/data/2.5/forecast?lat=30.6212&lon=-96.3404&appid=${apikey}&units=imperial&cnt=1`

let client = new SmookControllerApi();

function httpGet(url) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function getWeather() {
    let resp = httpGet(weatherUrl);
    const weather = JSON.parse(resp);
    let forecast = weather.list[0];
    return `${forecast.weather[0].description}, ${forecast.main.temp}°`;
}

function setWeather() {
    document.getElementById("weatherMsg").textContent = getWeather();
}

let curIntervalId = null;

onMounted(() => {
    // Update the weather every minute
    curIntervalId = setInterval(setWeather, 60 * 1000);
})

onUnmounted(() => {
    // Stop auto-updating weather
    clearInterval(curIntervalId);
})

const categories = ref([]);

async function getCategories() {
    client.sendCategories((error, data, resp) => {
        if (data) {
            categories.value = data;
        }
    })
}

getCategories();

</script>
<template>
    <Heading />
    <main>
        <div id="main">
            <h1>Menu</h1>
            <h2 id="weatherMsg">{{ getWeather() }}</h2>
            <div class="menu-items">
                <ul>
                    <li v-for="cat in categories">{{ cat }}</li>
                </ul>
            </div>
        </div>
    </main>
</template>

<style scoped>
main {
  width: 100%;
  height: 100%;
  position: absolute;
  top:0;
  left:0;
}
#main {
  /* margin-top: 80px;
  height: 100%; */
  height: 89%;
  width: 100%;
  position: absolute;
  top: 75px;
  /* height: 1000px; */
}
</style>