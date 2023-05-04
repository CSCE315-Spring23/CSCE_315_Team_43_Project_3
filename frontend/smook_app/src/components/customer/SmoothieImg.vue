<script setup>
    const props = defineProps(['name'])
</script>
<template>
    <div id="img">
        <img src="./imgs/smoothie1.png" alt="Image of Smoothie">
    </div>
</template>
<style scoped>
    img {
        display: block;
        margin: 0 auto;
        width: 330px;
    }
</style>