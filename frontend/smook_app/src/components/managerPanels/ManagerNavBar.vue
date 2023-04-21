<script setup>

    function switch_page() {
        console.log(user.value);
        if (user.value == "server")
            router.replace('/server')
        else if (user.value == "manager")
            router.replace('/manager')
        else
            err.value = 'bad';
        console.log(err);
    }
</script>

<template>
    <ul class="nav_bar">
        <li class="nav_elem"><a href="Inventory">Inventory</a></li>
        <li class="nav_elem"><a href="Menu">Menu</a></li>
        <li class="nav_elem"><a href="EditMenu">Edit Menu Item</a></li>
        <li class="nav_elem"><a href="AddMenu">Add Menu Item</a></li>
        <li class="nav_elem"><a href="OrderInventory">Order Inventory</a></li>
        <li class="nav_elem"><a href="AddInventory">Add Inventory Item</a></li>
        <li class="nav_elem"><a href="XReport">X Report</a></li>
        <li class="nav_elem"><a href="ZReport">Z Report</a></li>
    </ul>
</template>
<style scoped>
    li a {
        display: block;
        color: #000;
        text-decoration: none;
        padding: 5% 0%;
    }
    .nav_bar {
        list-style-type: none;
        margin: 0;
        padding: 0;
        width: 12%;
        background-color: #c2c2c2;
        height: 100%;
        position: fixed;
        overflow: auto;
    }
    .nav_elem{
        margin-left: 5%;
    }
</style>