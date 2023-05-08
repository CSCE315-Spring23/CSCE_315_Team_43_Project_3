<script setup>
const props = defineProps(['msg']);

</script>

<template>
    <div id="weather_API">
        <h4>{{ msg }}</h4>
    </div>
</template>

<style scoped>
    #weather_API {
        border: 2px black solid;
        padding: 5px;
        width: 100px;
        position: absolute;
        bottom: 0;
    }
</style>