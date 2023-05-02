<script setup>
    import { useItemStore } from '@/stores/CurrentItem';
    const itemStore = useItemStore();
</script>

<template>
    <div id="recipe" role="list">
        <div role="listitem" v-for="item in itemStore.ingredients">
            <h5 class="currIng">{{ item.name }}</h5>
            <select id="quantity-select" v-model="item.quanity">
                <option value="0">Remove</option>
                <option value="1">Less</option>
                <option value="2" selected>Standard</option>
                <option value="3">More</option>
            </select>
        </div>
    </div>
</template>

<style scoped>

</style>