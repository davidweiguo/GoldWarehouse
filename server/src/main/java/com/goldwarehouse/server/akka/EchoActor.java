package com.goldwarehouse.server.akka;

import akka.actor.AbstractActor;

/**
 * @author guo_d
 * @date 2018/9/30
 */
public class EchoActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Echo.class, this::onEcho).build();
    }

    private void onEcho(Echo echo) {
        System.out.println(this.getSelf().toString() + "," + echo.getNum() + ", " + System.currentTimeMillis());
        getSender().tell("feedback", getSelf());
    }

    public static class Echo {
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
