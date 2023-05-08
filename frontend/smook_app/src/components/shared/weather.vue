<script setup>
const props = defineProps(['msg']);

</script>

<template>
    <span id="weather_API">
        <h3>Weather Now:</h3>
        <h4>{{ msg }}</h4>
    </span>
</template>

<style scoped>
    #weather_API {
        /* border: 2px black solid; */
    }
    /* h3 {
        text-align: center;
    } */
    h4 {
        text-align: right;
        margin-right: 20px;
    }
</style>