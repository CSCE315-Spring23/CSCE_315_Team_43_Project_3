<template>
  <v-app>
    <v-main>
      <v-container>
        <h2>Inventory</h2>
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
console.log("here");
export default {
    props: ["posts"], 
      data: () => ({
          search: "",
          headers: [
              { title: 'Inventory ID', align: 'start', sortable: true, key: 'inventoryId' },
              { title: 'Measurement Type', key: 'measurementType'},
              { title: 'Name', key: 'name' },
              { title: 'Price', key: 'price' },
              { title: 'Quantity', key: 'quantity'},
              { title: 'Restock Amount', key: 'restockAmount'}  
          ],
      })
};
</script>