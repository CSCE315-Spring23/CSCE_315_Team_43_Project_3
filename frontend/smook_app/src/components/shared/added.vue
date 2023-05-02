<script setup>
    import { useItemStore } from '@/stores/CurrentItem';
    const itemStore = useItemStore();
</script>

<template>
    <div id="currentIngredient">
        <h5 class="currIng"></h5>
    </div>
</template>

<style scoped>

</style>