package com.ordering.charlene;

import com.ordering.charlene.factories.CoffeeFactory;
import com.ordering.charlene.factories.OrderFactory;
import com.ordering.charlene.model.*;
import com.ordering.charlene.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoffeeOrderParser {

    private OrderFactory orderFactory = new OrderFactory();

    public List<Object> parseOrder(String order,OrderSystem orderSystem) {
        String[] items = order.toLowerCase().split(", ");
        List<Object> orderList = new ArrayList<>();

        Arrays.stream(items).forEach( item -> {
            String[] tokens = item.split(" ");
            if (tokens.length > 1 && tokens[1].equals("coffee")) {
                String size = tokens[0];
                Beverage beverage = orderFactory.createBeverage(Constants.BEVERAGE_TYPE_COFFEE, size.toLowerCase());
                if (item.contains("extra milk")) {
                    beverage = CoffeeFactory.addExtraMilk(beverage);
                }
                if (item.contains("foamed milk")) {
                    beverage = CoffeeFactory.addFoamedMilk(beverage);
                }
                if (item.contains("special roast")) {
                    beverage = CoffeeFactory.addSpecialRoast(beverage);
                }
                orderSystem.addBeverage(beverage);
                orderList.add(beverage);
            } else if (tokens.length > 1 && tokens[1].equals("juice")) {
                Beverage beverage =orderFactory.createBeverage(Constants.BEVERAGE_TYPE_JUICE, tokens[0]);
                orderList.add(beverage);
                orderSystem.addBeverage(beverage);
            } else if (tokens[0].equals("bacon")) {
                Snack bacon = new Bacon();
                orderList.add(bacon);
                orderSystem.addSnack(bacon);
            }
        });

        return orderList;
    }

    public static void main(String[] args) {
        CoffeeOrderParser parser = new CoffeeOrderParser();
        OrderSystem orderSystem = OrderSystem.getInstance();

        // Example order
        String order = "large coffee with extra milk, small coffee with special roast, bacon, orange juice, medium coffee with extra milk, orange juice";
        List<Object> orderList = parser.parseOrder(order,orderSystem);
        orderSystem.applyDiscounts();
        /*double totalCost = 0;
        for (Object item : orderList) {
            if (item instanceof Beverage beverage) {
                System.out.println(beverage.getDescription() + " $" + beverage.price());
                if(beverage.getDescription().indexOf(Constants.COUPON_FREE) == -1)
                   totalCost += beverage.price();
            } else if (item instanceof Snack snack) {
                System.out.println(snack.getDescription() + " $" + snack.price());
                if(!snack.getDescription().contains(Constants.COUPON_FREE))
                    totalCost += snack.price();
            }
        }*/
        orderSystem.printOrder();
        System.out.println("Total Cost: $" + orderSystem.calculateTotalCost());
    }
}
