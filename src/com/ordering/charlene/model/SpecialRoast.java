package com.ordering.charlene.model;

public class SpecialRoast extends Beverage {
    Beverage beverage;

    public SpecialRoast(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", SpecialRoast";
    }

    @Override
    public double price() {
        return beverage.price() + CoffeeExtras.SPECIAL_ROAST.price;
    }}
