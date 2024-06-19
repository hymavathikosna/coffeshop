package com.ordering.charlene.model;

public class ExtraMilk extends Extras {
    Beverage coffee;

    public ExtraMilk(Beverage coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Extra Milk";
    }

    public double price() {
        return coffee.price()+CoffeeExtras.EXTRA_MILK.price;
    }
}

