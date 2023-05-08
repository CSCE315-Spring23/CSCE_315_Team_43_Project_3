import { defineStore } from "pinia";

export const useLoginStore = defineStore('cart', {
    state: () => ({
        loggedIn: false
    })
})