package com.ordering.charlene.factories;

import com.ordering.charlene.model.*;
import com.ordering.charlene.util.Constants;

public class OrderFactory {
    public Beverage createBeverage(String type, String detail) {
        if (type.equalsIgnoreCase(Constants.BEVERAGE_TYPE_COFFEE)) {
           return CoffeeFactory.createCoffee(detail);
        } else if (type.equalsIgnoreCase(Constants.BEVERAGE_TYPE_JUICE)) {
            return new Juice(detail);
        }
        return null;
    }

    public Snack createSnack(String type) {
        if (type.equalsIgnoreCase(Constants.SNACK_SANDWICH)) {
            return new Sandwich();
        } else if (type.equalsIgnoreCase(Constants.SNACK_BACON)) {
            return new Bacon();
        }
        return null;
    }
}
