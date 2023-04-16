<template>
  <v-app>
    <v-main>
      <v-container>
        <h2>Vuetify Table</h2>
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
            { title: 'User ID', align: 'start', sortable: true, key: 'userId' },
            { title: 'ID', key: 'id' },
            { title: 'Title', key: 'title' },
            { title: 'Actions', key: 'actions'},
        ],
    })
};
</script>