<script setup>
    import VuetifyDatatable from "../../components/managerPanels/Table.vue";
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
        userId: 1,
        id: 1,
        title:
          "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
      },
      {
        userId: 1,
        id: 2,
        title: "qui est esse",
      },
      {
        userId: 1,
        id: 3,
        title: "ea molestias quasi exercitationem repellat qui ipsa sit aut",
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