package com.ordering.charlene.model;

public class FoamedMilk extends Beverage {
    Beverage beverage;

    public FoamedMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Foamed Milk";
    }

    @Override
    public double price()
    {
        return beverage.price() + CoffeeExtras.FOAMED_MILK.price;
    }
}
