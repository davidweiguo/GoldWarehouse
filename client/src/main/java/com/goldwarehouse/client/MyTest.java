package com.goldwarehouse.client;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.goldwarehouse.common.event.BasicRequestParameter;
import com.goldwarehouse.common.event.account.UserLoginEvent;
import com.goldwarehouse.common.event.account.UserLoginReplyEvent;
import com.goldwarehouse.common.event.server.ServerReadyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

// This is a test harness application
public class MyTest extends ClientAdaptor {
    private static final Logger log = LoggerFactory.getLogger(ClientAdaptor.class);

    @Override
    public void subscribeToEvents() {
        super.subscribeToEvents();
        subscribeToEvent(ServerReadyEvent.class, null);
        subscribeToEvent(UserLoginReplyEvent.class, null);
    }

    public void processServerReadyEvent(final ServerReadyEvent event) {

        Thread thread = new Thread(() -> {
            BasicRequestParameter requestParam = new BasicRequestParameter(getId(), null, getId());
            UserLoginEvent loginEvent = new UserLoginEvent(requestParam, "david", "pwd");
            sendEvent(loginEvent);
            try {
                Thread.sleep(3000000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.exit(0);
        });

        thread.start();
    }

    public void processUserLoginReplyEvent(UserLoginReplyEvent event) {
        log.info("Received UserLoginReplyEvent, ok: {}", event.isOk());
    }

    @Override
    public void processServerStatusEvent(String server, boolean up) {
    }

    public static void main(String[] args) throws Exception {
        String logfile = "client/conf/clogback.xml";
        String configFile = "client/conf/myclient.xml";

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            configurator.doConfigure(logfile);
        } catch (JoranException je) {
            je.printStackTrace();
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);

        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(configFile);
        // start server
        MyTest bean = (MyTest) applicationContext.getBean("myTest");
        bean.init();
    }
}
