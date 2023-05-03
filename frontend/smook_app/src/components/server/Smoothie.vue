<script setup>
import { useRouter } from 'vue-router'
import { useItemStore } from '@/stores/CurrentItem';
import { ref } from 'vue';
const props = defineProps(['item']);
const router = useRouter();
const itemStore = useItemStore();
itemStore.$reset();
const smoothieClick = () => {
  itemStore.changeName(props.item);
  router.push('/customer/OrderPage');
}
const itemName = ref("");
itemName.value = props.item.replace(/the\b/gi, "");
</script>

<template>
    <div id="smoothie" @click="smoothieClick">
        <h4>{{ itemName }}</h4>
        <!-- <ul v-if="ingredients.length>0">
          <li v-for="thing in ingredients" :key="thing">{{ thing }}</li>
        </ul> -->
    </div>
</template>

<style scoped>
  #smoothie {
    /* flex: 1; */
    /* min-width: 270px;
    max-width: 270px; */
    margin-bottom: 2px; /* set a margin between the rows */
    display: flex;
    justify-content: center;
    align-items: center;
    border: black solid 1px;
    margin-left: 5px;
    cursor: pointer;
  }
  .smoothieItem {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  h4 {
    font-size: 18px;
    text-align: center;
  }
  #smoothie:hover {
    background-color: rgb(105, 141, 207);
  }
</style>