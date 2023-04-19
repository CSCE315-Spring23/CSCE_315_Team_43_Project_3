class CartItem {
    constructor(smoothie, size, price, substitutions) {
        this.smoothie = smoothie;
        this.size = size;
        this.price = price;
        this.substitutions = substitutions;
    }

    // Getters
    getSmoothie() {
        return this.smoothie;
    }

    getSize() {
        return this.size;
    }

    getPrice() {
        return this.price;
    }

    getSubstitutions() {
        return this.substitutions;
    }

    // Setters
    setSmoothie(smoothie) {
        this.smoothie = smoothie;
    }

    setSize(size) {
        this.size = size;
    }

    setPrice(price) {
        this.price = price;
    }

    setSubstitutions(substitutions) {
        this.substitutions = substitutions;
    }
}