package com.goldwarehouse.server.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.File;

/**
 * Created by David on 2017/6/19.
 */
public class AkkaManager {

    private ActorSystem m_actorSystem;
    private ActorRef requestActor;
    private ActorRef responseActor;

    public static void main(String[] args) {
        AkkaManager manager = new AkkaManager();
//        manager.init();
//        manager.sayHello();
        manager.testEchoActor();
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


    ActorSystem system = null;
    private String echoName = "echo";
    private int actorNum = 3;

    private void testEchoActor() {
        system = ActorSystem.create("app");
        initActor();
//        for (int i = 0; i < 100; i++) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            EchoActor.Echo echoObj = new EchoActor.Echo();
//            echoObj.setNum(i);
//            sendEcho(echoObj);
//        }
        EchoActor.Echo echoObj = new EchoActor.Echo();
        echoObj.setNum(19);
        askEcho(echoObj);

    }

    private void initActor() {
        for (int i = 0; i < actorNum; i++) {
            ActorRef echoActor = system.actorOf(Props.create(EchoActor.class), echoName + i);
            System.out.println(echoActor.path());
        }
    }

    private void sendEcho(EchoActor.Echo echo) {
        String suffix = String.valueOf(echo.getNum() % actorNum);
        ActorSelection as = system.actorSelection("//app/user/" + echoName + suffix);
        Future<ActorRef> fu = as.resolveOne(new Timeout(Duration.create(1, "seconds")));
        fu.onComplete(new OnComplete<ActorRef>() {
            @Override
            public void onComplete(Throwable failure, ActorRef success) throws Throwable {
                if (failure != null) {
                    failure.printStackTrace();
                    ActorRef echoActor = system.actorOf(Props.create(EchoActor.class), echoName + suffix);
                    echoActor.tell(echo, ActorRef.noSender());
                } else {
                    success.tell(echo, ActorRef.noSender());
                }
            }
        }, system.dispatcher());
    }

    private void askEcho(EchoActor.Echo echo) {
        String suffix = String.valueOf(echo.getNum() % actorNum);
        ActorSelection as = system.actorSelection("//app/user/" + echoName + suffix);
        Future<Object> rt = Patterns.ask(as, echo, new Timeout(Duration.create(1, "seconds")));
        rt.onComplete(new OnComplete<Object>() {
            @Override
            public void onComplete(Throwable failure, Object success) throws Throwable {
                if (failure != null) {
                    failure.printStackTrace();
                    System.out.println("!!!!!!!!TimeOut!!!!!!");
                } else {
                    System.out.println("Received return value: " + success);
                }
            }
        }, system.dispatcher());
        System.out.println("finished");
    }
}
