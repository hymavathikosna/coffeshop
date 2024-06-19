package com.ordering.charlene;
import com.ordering.charlene.model.Beverage;
import com.ordering.charlene.model.Snack;

import java.util.List;

public interface DiscountStrategy {
    void applyDiscount(List<Beverage> beverages, List<Snack> snacks);
}
