package com.ordering.charlene.model;
import com.ordering.charlene.util.Constants;

public class Coffee extends Beverage {

    public Coffee(String size) {
        setSize(size);
        description = size + " Coffee";
    }

    public double price() {
        return switch (size.toLowerCase()) {
            case Constants.COFFEE_SMALL -> 2.5;
            case Constants.COFFEE_MEDIUM ->  3.0;
            case Constants.COFFEE_LARGE ->  3.5;
            default ->  3.0;
        } ;
    }
}
