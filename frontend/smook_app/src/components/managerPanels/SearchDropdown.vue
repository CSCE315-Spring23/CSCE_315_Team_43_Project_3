<script setup lang="ts">
import {
  TheDropDownNavbar,
  TheDropDownMenu,
  TheDropDownItem,
  TheDropDownDivideBlock,
} from "vue3-dropdown-navbar";
import { ref } from "vue";

const dropdownMenu = ref<InstanceType<typeof TheDropDownMenu>>();
const toggleDarkMode = () => {
  document.documentElement.classList.toggle("dd-nav-dark");
}; 
</script>

<template>
  <TheDropDownNavbar>
    <template #logo>
      <TheDropDownNavbarLogo>Vue3 DropDown Navbar</TheDropDownNavbarLogo>
    </template>
    <TheDropDownItem link="/home">Home</TheDropDownItem>
    <TheDropDownMenu text="Dropdown 1" ref="dropdownMenu">
      <TheDropDownItem>Dropdown Item 1</TheDropDownItem>
      <TheDropDownItem>Dropdown Item 2</TheDropDownItem>
      <TheDropDownMenu text="Dropdown Item 3">
        <TheDropDownItem>Dropdown Item 3-1</TheDropDownItem>
        <TheDropDownItem>Dropdown Item 3-2</TheDropDownItem>
      </TheDropDownMenu>
      <TheDropDownDivideBlock>
        <TheDropDownItem>Sign out</TheDropDownItem>
      </TheDropDownDivideBlock>
    </TheDropDownMenu>
    <TheDropDownItem @click="toggleDarkMode">Toggle Dark Mode</TheDropDownItem>
  </TheDropDownNavbar>

  <button
    class="border bg-blue-500 text-white px-4 py-2 rounded-lg"
    @click="dropdownMenu?.openDropdownMenu"
  >
    Open Dropdown Menu
  </button>
</template>