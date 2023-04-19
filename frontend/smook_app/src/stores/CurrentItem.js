import { defineStore } from "pinia";

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
        changePrice() {
          this.setState((state) => ({ price: state.price + 1 }));
        },
        changeName(name) {
          this.name = name;
        },
        changeSize(s){
          this.size = s;
          if (s == 'small')
          this.price = 6.99
          else if (s=='medium')
          this.price = 8.99
          else
          this.price = 10.99
        }
      }
})