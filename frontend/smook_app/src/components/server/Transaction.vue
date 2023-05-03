<script setup>
import {useCartStore} from '@/stores/CartStore';
import { ref } from 'vue';
const cartStore = useCartStore();
// const subtotal = ref(0.0);
// const tax = ref(0.0);
// const total = ref(0.0);
// function totaler() {
//   console.log("Totaller");
//   subtotal.value = 0.0;
//   tax.value = 0.0;
//   total.value = 0.0;
//     for(let i = 0; i<cartStore.cart.length; i++){
//         let item = cartStore.cart[i];
//         subtotal.value += item.price;
//     }
//     tax.value = subtotal.value*.0825;
//     total.value = tax.value + subtotal.value;
//     subtotal.value = subtotal.value.toLocaleString('en-US', {
//             style: 'currency',
//             currency: 'USD'
//           })
//     tax.value = tax.value.toLocaleString('en-US', {
//         style: 'currency',
//         currency: 'USD'
//         })
//     total.value = total.value.toLocaleString('en-US', {
//             style: 'currency',
//             currency: 'USD'
//           })
// }
// totaler();
function submit() {
    let name = prompt("What's their name?");
    if (name != null){
      cartStore.send(name);
      cartStore.$reset();
    }
}
</script>

<template>
    <Heading />
  <div id="main">
    <h1>Current Order</h1>
    <div id="cartItems">
        <div v-for="item in cartStore.cart">
            <h3>{{ item.name }} ({{ item.size }})- {{ item.price.toLocaleString('en-US', {
            style: 'currency',
            currency: 'USD'
          }) }}</h3>
          <button @click="cartStore.removeItem(item);totaler()" id="xButton">x</button>
        </div>
    </div>
    <div id="price">
        <h5>Subtotal: {{ cartStore.getSubtotal }}</h5>
        <h5>Tax: {{ cartStore.getTax }}</h5>
        <h5>Total: {{ cartStore.getTotal }}</h5>
    </div>
    <button @click="submit">Pay and Order</button>
  </div>
</template>

<style scoped>
#xButton {
    display: inline;
    padding: 0;
    background-color: red !important;
    margin-left: 10px;
}
h3 {
  display: inline;
}
#main {
  padding: 12px;
}
#cartItems {
  border: 1px solid #ddd;
  padding: 10px;
  margin: 20px 0;
}
#cartItems h3 {
  margin: 5px 0;
  font-size: 18px;
}
#cartItems h3 span:nth-child(1) {
  margin-right: 10px;
}
#price {
  margin-top: 20px;
}
#price h5 {
  margin: 5px 0;
  font-size: 16px;
  font-weight: normal;
}
#price h5:first-child {
  font-weight: bold;
}
#main button {
  background-color: #008CBA;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px;
  font-size: 18px;
  cursor: pointer;
}
#main button:hover {
  opacity: 0.8;
}
#main button:focus {
  outline: none;
  box-shadow: 0 0 0 2px #008CBA;
}
</style>