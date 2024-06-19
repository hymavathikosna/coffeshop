package com.ordering.charlene.model;

import com.ordering.charlene.util.Constants;

public class Bacon extends Snack {
    public Bacon() {
        description = Constants.SNACK_BACON;
    }

    public double price() {
        return 4.5;
    }
}
