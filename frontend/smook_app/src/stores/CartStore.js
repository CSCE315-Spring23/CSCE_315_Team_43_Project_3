import { defineStore } from "pinia";
import axios from 'axios';
  
  // Add cors middleware
  
export const useCartStore = defineStore('cart', {
    state: () => ({
        cart: [],
        subtotal: 0.0,
        tax: 0.0,
        total: 0.0
    }),
    getters: {
        cartSize(state){
            return state.cart.length
        },
        getSubtotal(){
            return this.subtotal.toLocaleString('en-US', {
                style: 'currency',
                currency: 'USD'
              })
        },
        getTax(){
            return this.tax.toLocaleString('en-US', {
                style: 'currency',
                currency: 'USD'
              })
        },
        getTotal(){
            return this.total.toLocaleString('en-US', {
                style: 'currency',
                currency: 'USD'
              })
        }
    },
    actions: {
        addItem(item){
            this.cart.push(item);
            this.subtotal += item.price;
            this.tax = this.subtotal*.0825;
            this.total = this.subtotal + this.tax;
        },
        deleteItem(i){
            let p = this.cart[i].price;
            this.cart.splice(i,1);
            this.subtotal -= p;
            this.tax = this.subtotal*.0825;
            this.total = this.subtotal + this.tax;
        },
        removeItem(item){
            for (let i = 0; i<this.cart.length; i++){
                if (this.cart[i]===item){
                    let p = this.cart[i].price;
                    this.cart.splice(i,1);
                    this.subtotal -= p;
                    this.tax = this.subtotal*.0825;
                    this.total = this.subtotal + this.tax;
                    this.deleteItem(i);
                }
            }
        },
        async send(name){
            console.log("sending" + name);
            let str = "https://smook-app.uc.r.appspot.com/api/addTransaction?";
            str += "smoothieQuantity=" + this.cart.length + "&employeeId=1&name=" + name;
            for (let i = 0; i<this.cart.length; i++){
                let Sname = this.cart[i].name;
                let size = this.cart[i].size;
                let ingredients = this.cart[i].ingredients;
                let price = this.cart[i].price;
                str += "&smoothieName=" + Sname.replace(/ /g, "%20");
                str += "&size=";
                switch (size) {
                    case 'S':
                        str += 'small';
                        break;
                    case 'M':
                        str += 'medium';
                        break;
                    case 'L':
                        str += 'large';
                        break;
                    default:
                        str += 'bruuuuuh';
                        break;
                }
                str += "&price=" + price;
                str += "&numIngredients=" + ingredients.length;
                for (let f = 0; f<ingredients.length; f++){
                    str += "&ingredientName=" + ingredients[f].replace(/ /g, "%20");
                    str += "&itemQuantity=" + 1;
                }
            }
            console.log(str);
            try {axios.post(str);}
            catch{
                console.log("Thats bad");
            }
            this.cart.slice(0,this.cart.length);
            this.subtotal = 0.0;
            this.tax = 0.0;
            this.total = 0.0;
        }
    }
})