package com.goldwarehouse.design.simplefactory;

public abstract class AbstractPizzaStore implements PizzaStore {
    @Override
    public final Pizza orderPizza(PizzaType type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(PizzaType type);
}
