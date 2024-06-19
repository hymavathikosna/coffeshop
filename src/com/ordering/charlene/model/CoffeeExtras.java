package com.ordering.charlene.model;

public enum CoffeeExtras {
    EXTRA_MILK(0.3),
    FOAMED_MILK(0.5),
    SPECIAL_ROAST(0.9);
    public final double price;
    CoffeeExtras(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
}
