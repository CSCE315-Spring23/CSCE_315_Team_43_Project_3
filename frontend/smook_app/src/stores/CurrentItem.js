import { defineStore } from "pinia";

export const useItemStore = defineStore('cItem', {
    state: () => ({
        name: "",
        ingredients: [],
        image: "",
        price: 0.0
    }),
    getters: {
        getName(state){
            return state.cItem.name
        }
    },
    actions: {
        addItem(item){
            this.cart.push(item);
        }
    }
})