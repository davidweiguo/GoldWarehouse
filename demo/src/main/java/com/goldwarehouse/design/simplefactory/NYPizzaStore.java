package com.goldwarehouse.design.simplefactory;

public class NYPizzaStore extends AbstractPizzaStore {
    @Override
    protected Pizza createPizza(PizzaType type) {
        Pizza pizza;
        switch (type) {
            case CHEESE:
                pizza = new NYStyleCheesePizza();
                break;
            case PEPPERONI:
                pizza = new NYStylePepperoniPizza();
                break;
            case CLAM:
                pizza = new NYStyleClamPizza();
                break;
            case VEGGIE:
                pizza = new NYStyleVeggiePizza();
                break;
            default:
                pizza = null;
        }
        return pizza;
    }
}
