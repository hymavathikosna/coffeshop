package com.ordering.charlene;
import com.ordering.charlene.model.Beverage;
import com.ordering.charlene.model.Extras;
import com.ordering.charlene.model.Snack;
import com.ordering.charlene.util.Constants;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class FreeToppingWithBeverageAndSnackStrategy implements DiscountStrategy {
    @Override
    public void applyDiscount(List<Beverage> beverages, List<Snack> snacks) {
        boolean hasBeverageAndSnack = !beverages.isEmpty() && !snacks.isEmpty();
        if (hasBeverageAndSnack) {
            AtomicInteger counter = new AtomicInteger();
            counter.set(-1);
            int index = beverages.stream().filter(item -> {
                        counter.getAndIncrement();
                return item instanceof Extras;
                    }).mapToInt(item -> counter.get())
                    .findFirst().orElse(-1);
            if(index != -1) {
                Beverage firstExtra = beverages.get(index);
                beverages.forEach(item -> {
                    if(item.getDescription().equalsIgnoreCase(firstExtra.getDescription())){
                        item.setDescription(firstExtra.getDescription() + Constants.COUPON_TOPPING_FREE);
                    }else{
                        item.setDescription(item.getDescription() );
                    }
                });
            }
        }
    }

}
