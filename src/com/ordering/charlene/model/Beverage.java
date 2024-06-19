package com.ordering.charlene.model;

import com.ordering.charlene.util.Constants;

public abstract class Beverage {
    public String description = "";
    public String size = ""; // Default size

    public String getDescription() {
        return description + " (" + size + ")";
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }


    public abstract double price();
}
