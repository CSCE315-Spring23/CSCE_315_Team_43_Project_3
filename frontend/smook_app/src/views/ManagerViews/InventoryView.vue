<script setup>
    import VuetifyDatatable from "../../components/managerPanels/InventoryTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
    import axios from 'axios';
</script>

<script>
export default {
  name: "App",

  components: {
    VuetifyDatatable,
  },

  data: () => ({
    posts: [
      {
        inventoryID: 1,
        name: "100% Cocoa",
        price: 1, 
        quantity: 426, 
        measurementType: "oz"
      },
      {
        inventoryID: 2,
        name: "Almond Butter",
        price: 1, 
        quantity: 186, 
        measurementType: "oz"
      },
      {
        inventoryID: 3,
        name: "Almonds",
        price: 1, 
        quantity: 468, 
        measurementType: "oz"
      },
      {
        inventoryID: 4,
        name: "Apple Blueberry Juice Blend",
        price: 1, 
        quantity: 165, 
        measurementType: "oz"
      },
      {
        inventoryID: 5,
        name: "Apple Juice Blend",
        price: 1, 
        quantity: 184, 
        measurementType: "oz"
      },
      {
        inventoryID: 6,
        name: "Apple Pineapple Juice Blend",
        price: 1, 
        quantity: 206, 
        measurementType: "oz"
      },
    ]
  }),
};
</script>

<template>
  <h1>Manager</h1>
  <div>
    <VuetifyDatatable :posts="posts" />
  </div>
</template>