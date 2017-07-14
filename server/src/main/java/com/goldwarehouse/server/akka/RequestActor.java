package com.goldwarehouse.server.akka;

import akka.actor.AbstractActor;

public class RequestActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, s -> print(s)).build();
    }

    private void print(String s) {
        System.out.println(s);
    }
}
