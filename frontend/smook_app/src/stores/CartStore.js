import { defineStore } from "pinia";
import axios from 'axios';
  
  // Add cors middleware
  
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
            console.log("sending" + name);
            axios.post("/api/transaction?smoothieQuantity=2&employeeId=1&name=Fred&size=small&price=6.19&smoothieName=Gladiator%20Coffee%20Cold%20Brew&numIngredients=2&ingredientName=Gladiator%20Protein%20Chocolate&ingredientName=Bananas&itemQuantity=1&itemQuantity=2&smoothieName=Gladiator%20Coffee%20Espresso&numIngredients=3&ingredientName=Gladiator%20Protein%20Chocolate&ingredientName=Bananas&ingredientName=Espresso&itemQuantity=1&itemQuantity=1&itemQuantity=1");
        }
    }
})