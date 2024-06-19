package com.ordering.charlene;

import com.ordering.charlene.model.Beverage;
import com.ordering.charlene.model.Snack;
import com.ordering.charlene.util.Constants;

import java.util.List;
public class FreeBeverageForFifthDrinkStrategy implements DiscountStrategy {
    @Override
    public void applyDiscount(List<Beverage> beverages, List<Snack> snacks) {
        if (beverages.size() >= 5) {
            beverages.get(4).description += Constants.COUPON_FREE;
        }
    }
}
