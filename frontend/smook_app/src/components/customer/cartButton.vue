<script setup>
    import { useRouter } from 'vue-router'
    import {useCartStore} from '@/stores/CartStore'
    const cartStore = useCartStore();
    const router = useRouter();
    function cart() {
        router.push('/customer/cart');
    }
</script>
<template>
    <div @click="cart" id="cartButton" role="button" tabindex="0" aria-label="Go to cart screen" @keydown.enter="cart">
        <img src="./imgs/cart.png" alt="Shopping Cart">
        <p>{{ cartStore.cart.length }}</p>
    </div>
</template>
<style scoped>
    #cartButton {
        width: 90px;
        height: 90px;
        border-radius: 45px;
        background-color: lightgreen;
        position: fixed;
        bottom: 10%;
        right: 3%;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
    }
    #cartButton img {
        width: 50px;
    }
    #cartButton p {
        margin-left: 5px;
    }
</style>