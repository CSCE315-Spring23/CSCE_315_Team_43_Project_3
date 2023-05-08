<script setup>
import Heading from '../components/Heading.vue';
import SmookControllerApi from '../classes/api/SmookControllerApi';
import { ref, onMounted, onUnmounted } from 'vue';
import weather from '../components/shared/weather.vue';
const apikey = "77d7af00a098e224eca5ce31cafde321";
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
                console.log(categories)
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
                    price: 0,
                };
                menuData.value.get(category).push(item);
                getMenuItemPriceByName(category, item.name);
            }
        }
    })
}

async function getMenuItemPriceByName(category, name) {
    client.sendPriceOfItem(name, (error, price, resp) => {
        if (price) {
            menuData.value.get(category).find(x => x.name === name).price = price;
        }
    })
}

getCategories();

</script>
<template>
    <Heading />
    <main>
      <div id="main">
        <h1>Smoothie King Menu</h1>
        <!-- <h2 id="weatherMsg">{{ weatherMsg }}</h2> -->
        <weather 
        :msg=weatherMsg
        />
        <div class="menu-items">
          <div v-for="[cat, items] in menuData" class="category-container">
            <h3>{{ cat }}</h3>
            <ul>
              <li v-for="item in items" class="menu-item">
                <div class="item-name">{{ item.name }}</div>
                <div class="item-price">$ {{ item.price }}</div>
              </li>
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
      top: 0;
      left: 0;
    }
  
    #main {
      height: 89%;
      width: 100%;
      position: absolute;
      top: 75px;
    }
    h1 {
        text-align: center;
    }
    h3 {
        font-weight: 600;
        text-align: center;
    }
    .menu-items {
        /* display: flex;
        flex-direction: column;
  flex-wrap: wrap;
  margin: 0 auto;
  align-content: flex-start */
  display: flex;
  flex-wrap: wrap;
  height: 100vh;
}
  
    .category-container {
        /* flex-basis: 30%;
  max-width: 300px;
  margin: 0 10px 40px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #f9f9f9; */
  flex: 1 0 auto;
  min-width: 200px;
  height: auto;
  background-color: #f2f2f2;
  margin: 10px;
    }
  
    .menu-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 18px;
      line-height: 1.5;
      margin-bottom: 10px;
      border-bottom: 1px black dotted;
    }
  
    .item-name {
    }
  
    .item-price {
      margin-left: 5px;
      white-space: nowrap;
    }
  </style>
  