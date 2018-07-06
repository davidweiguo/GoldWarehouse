package com.goldwarehouse.design.simplefactory;

public class ChicagoPizzaStore extends AbstractPizzaStore {
    @Override
    protected Pizza createPizza(PizzaType type) {
        Pizza pizza;
        switch (type) {
            case CHEESE:
                pizza = new ChicagoStyleCheesePizza();
                break;
            case PEPPERONI:
                pizza = new ChicagoStylePepperoniPizza();
                break;
            case CLAM:
                pizza = new ChicagoStyleClamPizza();
                break;
            case VEGGIE:
                pizza = new ChicagoStyleVeggiePizza();
                break;
            default:
                pizza = null;
        }
        return pizza;
    }
}
