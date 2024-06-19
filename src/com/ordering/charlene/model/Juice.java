package com.ordering.charlene.model;

import com.ordering.charlene.util.Constants;

public class Juice extends Beverage {
    private String type;

    @Override
    public String getDescription() {
        return description;
    }
    public Juice(String type) {
        this.type = type;
        description = type + " Juice";
    }

    public double price() {
        return switch (type.toLowerCase()) {
            case Constants.JUICE_ORANGE -> 3.95;
            default ->  3.95;
        };
    }
}
