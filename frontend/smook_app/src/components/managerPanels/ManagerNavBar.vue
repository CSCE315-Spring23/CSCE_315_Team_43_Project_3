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
        <li class="nav_elem"><a href="Inventory" tabindex="1">Inventory</a></li>
        <li class="nav_elem"><a href="www.google.com">End of Day</a></li>
        <li class="nav_elem"><a href="www.google.com">Order Inventory</a></li>
        <li class="nav_elem"><a href="www.google.com">View/Edit Item</a></li>
        <li class="nav_elem"><a href="www.google.com">Edit Menu</a></li>
        <li class="nav_elem"><a href="www.google.com">Analytics</a></li>
        <li class="nav_elem"><a href="www.google.com">Generate Reports</a></li>
        <li class="nav_elem"><a href="www.google.com">Item Pairs</a></li>
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