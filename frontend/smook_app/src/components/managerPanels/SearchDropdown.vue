<SearchDropdown
   :options="countries"
    v-on:selected="onSelectedOption"
    v-on:filter="getDropdownValues"
>
</SearchDropdown>

<script setup>
  import SearchDropdown from 'search-dropdown-vue'
  import {reactive, ref} from 'vue'
  const countries = ref([
      {id:1, name:'Morocco'},
      {id:2, name:'USA'},
      {id:3,name: "Canada"}
    ])
  let object = reactive({id:null, name: null})

  const onSelectedOption = (payload) => object = payload
</script>