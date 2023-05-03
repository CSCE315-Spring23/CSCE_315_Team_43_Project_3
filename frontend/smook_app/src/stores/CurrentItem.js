import { defineStore } from "pinia";
import axios from 'axios';

export const useItemStore = defineStore('itemStore', {
    state: () => ({
        name: "",
        size: 'none',
        ingredients: [],
        price: 0.0,
        initialCount: 0
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
          axios.get('https://smook-app.uc.r.appspot.com/api/ingredients', { params: { name: this.name } })
            .then(response => {
              const stuff = response.data;
              for (let i = 0; i<stuff.length; i++){
                this.ingredients.push(stuff[stuff.length-i-1]);
              }
              this.initialCount = this.ingredients.length;
            })
            .catch(error => {
              console.error(error);
            });
        },
        pushIngredient(ingredient) {
          this.ingredients.push(ingredient);
          if (this.ingredients.length >= this.initialCount)
          this.price += .99;
        },
        removeItem(item) {
          console.log("item");
          if (this.ingredients.length > this.initialCount)
          this.price -= .99;
          for (let i = 0; i<this.ingredients.length; i++){
            if (this.ingredients[i] == item){
              this.ingredients.splice(i,1);
            }
          }
        },
        async changeSize(s) {
          console.log("size called");
          this.size = s;
          if (s == 'small')
            this.size = 'S';
          else if (s == 'medium')
            this.size = 'M';
          else if (s == 'large')
            this.size = 'L';
          try {
            const response = await axios.get('/api/price', { params: { name: this.name } });
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