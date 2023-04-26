<script setup>
import Heading from '../components/Heading.vue';
const apikey = import.meta.env.VITE_OPENWEATHERMAP_API_KEY;
const weatherUrl = `https://api.openweathermap.org/data/2.5/forecast?lat=30.6212&lon=-96.3404&appid=${apikey}&units=imperial&cnt=1`

function httpGet(url) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

// Update the weather every minute
setInterval(setWeather, 1 * 1000);

function getWeather() {
    let resp = httpGet(weatherUrl);
    const weather = JSON.parse(resp);
    let forecast = weather.list[0];
    return `${forecast.weather[0].description}, ${forecast.main.temp}°`;
}

function setWeather() {
    document.getElementById("weatherMsg").textContent = getWeather();
}

</script>
<template>
    <Heading />
    <main>
        <div id="main">
            <h1>Menu</h1>
            <h2 id="weatherMsg">{{ getWeather() }}</h2>
            <div class="menu-items">
                <!-- Placeholder for menu items -->
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