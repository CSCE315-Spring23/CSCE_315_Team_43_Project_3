<template>
  <v-app>
    <v-main>
      <v-container>
        <h2>Z Report</h2>
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
            { title: 'ID', key: 'id'},
            { title: 'Name', align: 'start', sortable: true, key: 'name' },
            { title: 'Type', key: 'type' },
            { title: 'Price', key: 'price'},
            { title: 'Quantity Sold', key: 'quantitySold' }
        ],
    })
};
</script>