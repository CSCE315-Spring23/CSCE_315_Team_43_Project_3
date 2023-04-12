import { defineStore } from "pinia";

export const useCartStore = defineStore('cart', {
    state: () => ({
        cart: []
    }),
    getters: {
        cartSize(state){
            return state.cart.length
        }
    },
    actions: {
        addItem(item){
            this.cart.push(item);
        }
    }
})