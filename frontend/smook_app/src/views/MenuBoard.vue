<script setup>
import Heading from '../components/Heading.vue';
let apikey = import.meta.env.VITE_OPENWEATHERMAP_API_KEY;

function httpGet(url) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function getWeather() {
    let resp = httpGet(`https://api.openweathermap.org/data/2.5/forecast?lat=30.6212&lon=-96.3404&appid=${apikey}&units=imperial&cnt=1`);
    const weather = JSON.parse(resp);
    let forecast = weather.list[0];
    return `${forecast.weather[0].description}, ${forecast.main.temp}°`
}

</script>
<template>
    <Heading />
    <h1>Menu</h1>
    <h2>{{ getWeather() }}</h2>
</template>