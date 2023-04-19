import { defineStore } from "pinia";

export const useCartStore = defineStore('cart', {
    state: () => ({
        cart: []
    }),
    getters: {
        cartSize(state){
            return state.cart.length
        },
        getItem(i){
            return this.cart[i];
        }
    },
    actions: {
        addItem(item){
            this.cart.push(item);
        },
        deleteItem(i){
            this.cart.splice(i,1);
        },
        removeItem(item){
            for (let i = 0; i<this.cart.length; i++){
                if (this.cart[i]===item){
                    this.deleteItem(i);
                }
            }
        },
        send(name){
            console.log(name);
        }
    }
})