<template>
  <v-app>
    <v-main>
      <v-container>
        <h2>Menu</h2>
          <v-row>
            <v-col>
                <v-data-table 
                    :headers="headers"
                    :items="posts" 
                    :items-per-page="5" 
                    :search="search" 
                    class="elevation-1">
                </v-data-table>
            </v-col>
        </v-row>
     </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
    props: ["posts"], 
    data: () => ({
        search: "",
        headers: [
            { title: 'Menu ID', align: 'start', sortable: true, key: 'menuId' },
            { title: 'Ingredient Amount', key: 'ingredientAmount'},
            { title: 'Name', key: 'name' },
            { title: 'Price', key: 'price'},
            { title: 'Type', key: 'type' },
        ],
    })
};
</script>