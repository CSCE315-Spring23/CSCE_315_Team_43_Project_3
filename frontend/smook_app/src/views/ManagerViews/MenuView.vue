<script setup>
    import VuetifyDatatable from "../../components/managerPanels/MenuTable.vue";
    import NavBar from '../../components/managerPanels/ManagerNavBar.vue';
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
        menuID: 0,
        name: "Gladiator Coffee Cold Brew",
        type: "Feel Energized", 
        price:  6.19, 
        ingredient_amount: 4,
      },
      {
        menuID: 1,
        name: "Gladiator Coffee Espresso",
        type: "Feel Energized",
        price: 6.19, 
        ingredient_amount: 4,
      },
      {
        menuID: 2,
        name: "Coffee High Protein Almond Mocha",
        type: "Feel Energized",
        price: 6.09, 
        ingredient_amount: 8,
      },
      {
        menuID: 3,
        name: "Espresso High Protein Almond Mocha",
        type: "Feel Energized",
        price: 6.09, 
        ingredient_amount: 8,
      },
      {
        menuID: 4,
        name: "Vegan Coffee Mocha",
        type: "Feel Energized",
        price: 6.09, 
        ingredient_amount: 8,
      },
      {
        menuID: 5,
        name: "Apple Pineapple Juice Blend",
        type: "Feel Energized",
        price: "Feel Energized", 
        ingredient_amount: 7,
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