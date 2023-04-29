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

const weatherMsg = ref(getWeather());

function getWeather() {
    let resp = httpGet(weatherUrl);
    const weather = JSON.parse(resp);
    let forecast = weather.list[0];
    return `${forecast.weather[0].description}, ${forecast.main.temp}°`;
}

function setWeather() {
    // document.getElementById("weatherMsg").textContent = getWeather();
    weatherMsg.value = getWeather();
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

const menuData = ref(new Map());

async function getCategories() {
    client.sendCategories((error, categories, resp) => {
        if (categories) {
            for (let category of categories) {
                menuData.value.set(category, []);
                getMenuItemsByCategory(category)
            }
        }
    })
}

async function getMenuItemsByCategory(category) {
    client.sendItemsInCategory(category, (error, menuItems, resp) => {
        if (menuItems) {
            for (let menuItem of menuItems) {
                let item = {
                    name: menuItem,
                    price: "",
                };
                menuData.value.get(category).push(item);
                // menuData.value.set(category, menuItems);
            }
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
            <h2 id="weatherMsg">{{ weatherMsg }}</h2>
            <div class="menu-items">
                <div v-for="[cat, _] in menuData" class="category-container">
                    <h3>{{ cat }}</h3>
                    <ul>
                        <li v-for="menuItem in menuData.get(cat)">{{ menuItem.name }}</li>
                    </ul>
                </div>
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

.menu-items {
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    flex-direction: row;
    align-items: flex-start;
}
</style>