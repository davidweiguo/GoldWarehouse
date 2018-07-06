package com.goldwarehouse.design.decorator;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        this.description = "Dark Roask";
    }

    @Override
    public double cost() {
        return 2.99;
    }
}
