package com.ordering.charlene.model;

public abstract class Snack {
    String description = "";
    public String getDescription() {
        return description;
    }

    public abstract double price();
}
