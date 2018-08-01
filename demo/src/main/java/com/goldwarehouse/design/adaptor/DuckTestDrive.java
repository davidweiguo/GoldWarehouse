package com.goldwarehouse.design.adaptor;

public class DuckTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdaptor = new TurkeyAdaptor(turkey);
        System.out.println("The Turkey says....");
        turkey.gobble();
        turkey.fly();

        System.out.println("The Duck says...");
        duck.quack();
        duck.fly();

        System.out.println("The TurkeyAdaptor says...");
        turkeyAdaptor.quack();
        turkeyAdaptor.fly();
    }
}
