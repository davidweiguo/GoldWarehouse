package com.goldwarehouse.server.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

/**
 * Created by David on 2017/6/19.
 */
public class AkkaManager {

    private ActorSystem m_actorSystem;
    private ActorRef requestActor;
    private ActorRef responseActor;

    public static void main(String[]args) {
        AkkaManager manager = new AkkaManager();
        manager.init();
        manager.sayHello();
    }

    private void init() {
        Config cfg = ConfigFactory.parseFile(new File("conf/akka.conf"));
        m_actorSystem = ActorSystem.create("fdtapp", cfg);
        requestActor = m_actorSystem.actorOf(Props.create(RequestActor.class), "Request");
        responseActor = m_actorSystem.actorOf(Props.create(ResponseActor.class), "Response");
    }

    private void sayHello() {
        requestActor.tell("hello", ActorRef.noSender());
    }
}
