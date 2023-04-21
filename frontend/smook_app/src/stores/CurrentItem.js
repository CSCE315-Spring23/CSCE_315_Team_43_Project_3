import { defineStore } from "pinia";
import axios from 'axios';

export const useItemStore = defineStore('itemStore', {
    state: () => ({
        name: "",
        size: 'none',
        ingredients: [],
        price: 0.0
    }),
    getters: {
        getPrice: (state) => state.price.toLocaleString('en-US', {
            style: 'currency',
            currency: 'USD'
          })
    },
    actions: {
        changeName(name) {
          this.name = name;
        },
        pushIngredient(ingredient) {
          this.ingredients.push(ingredient);
          this.price += .99;
        },
        async changeSize(s) {
          console.log("size called");
          this.size = s;
          try {
            const response = await axios.get('http://localhost:8080/price', { params: { name: this.name } });
            console.log("Response" + response.data);
            const p = response.data;
            this.price = p;
            if (s == 'small')
              this.price += 0;
            else if (s == 'medium')
              this.price += 1.20;
            else
              this.price += 2.40;
          } catch (error) {
            console.error(error);
          }
        }
      }
})