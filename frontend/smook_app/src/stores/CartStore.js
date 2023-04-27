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
            const api = axios.create({
                baseURL: '/api',
                headers: {
                  common: {
                    'Access-Control-Allow-Origin': '*',
                  },
                  post: {
                    'Content-Type': 'text/plain'
                  }
                }
              });
            let str = '';
            str += name;
            str += ',' + this.cart.length + ',';
            for (let i = 0; i<this.cart.length; i++) {
                let thisItem = this.cart[i];
                str += thisItem.name + ',';
                let thisItemIng = thisItem.ingredients;
                str += thisItemIng.length + ',';
                for (let j = 0; j<thisItemIng.length; j++){
                    str += thisItemIng[j] + ',';
                }
            }
            console.log(str);
            api.post('/transaction', str)
            .then(function (response) {
                console.log(response);
              })
              .catch(function (error) {
                console.log(error);
              });
        }
    }
})