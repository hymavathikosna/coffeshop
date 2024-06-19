package com.ordering.charlene.factories;

import com.ordering.charlene.model.*;

public class CoffeeFactory {
    public static Beverage createCoffee(String size){
        return new Coffee(size);
    }

    public static Beverage addExtraMilk(Beverage beverage) {
        return new ExtraMilk(beverage);
    }

    public static Beverage addFoamedMilk(Beverage beverage) {
        return new FoamedMilk(beverage);
    }

    public static Beverage addSpecialRoast(Beverage beverage) {
        return new SpecialRoast(beverage);
    }
}
