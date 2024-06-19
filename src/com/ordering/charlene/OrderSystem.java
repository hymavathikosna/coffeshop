package com.ordering.charlene;

import com.ordering.charlene.model.*;
import com.ordering.charlene.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class OrderSystem {
    private static OrderSystem instance;
    private List<Beverage> beverages;
    private List<Snack> snacks = new ArrayList<>();
    private List<DiscountStrategy> discountStrategies = new ArrayList<>();

    private OrderSystem() {
        discountStrategies.add(new FreeBeverageForFifthDrinkStrategy());
        discountStrategies.add(new FreeToppingWithBeverageAndSnackStrategy());
        beverages = new ArrayList<>();
    }

    public static synchronized OrderSystem getInstance() {
        if (instance == null) {
            instance = new OrderSystem();
        }
        return instance;
    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public void applyDiscounts() {
        for (DiscountStrategy strategy : discountStrategies) {
            strategy.applyDiscount(beverages, snacks);
        }
    }

    public void printOrder() {
        System.out.println("Order Summary:");
        for (Beverage beverage : beverages) {
            System.out.println(beverage.description + " - $" + beverage.price());
        }
        for (Snack snack : snacks) {
            System.out.println(snack.getDescription() + " - $" + snack.price());
        }
    }
    public void clearOrders() {
        beverages.clear();
        snacks.clear();
    }
    public List<Beverage> getBeverages() {
        return beverages;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }
    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Beverage beverage : beverages) {
            if(beverage.description.contains(Constants.COUPON_TOPPING_FREE)){
                double reduceCost =  switch (beverage) {
                    case ExtraMilk extraMilk -> CoffeeExtras.EXTRA_MILK.price;
                    case FoamedMilk foamedMilk -> CoffeeExtras.FOAMED_MILK.price;
                    case SpecialRoast specialRoast -> CoffeeExtras.SPECIAL_ROAST.price;
                    default -> 0.0;
                };
                totalCost += beverage.price()-reduceCost;
            }
            else if(!beverage.getDescription().contains(Constants.COUPON_FREE))
                totalCost += beverage.price();
        }
        for (Snack snack : snacks) {
            totalCost += snack.price();
        }
        return totalCost;
    }

}
