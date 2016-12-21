package com.goldwarehouse.client;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
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
    }

    public void processServerReadyEvent(final ServerReadyEvent event) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//				HistoricalPriceRequestEvent requestEvent = new HistoricalPriceRequestEvent(event.getKey(), event.getSender(), "01045.HK", "1", 500);
//				TargetPositionRequestEvent tpEvent = new TargetPositionRequestEvent("key", event.getSender(),
//						"x112233", "test1-FX", "AUDUSD", -100000, 0.83, false, false);
                System.out.println(System.currentTimeMillis());
//				sendEvent(requestEvent);
                try {
                    Thread.sleep(3000000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.exit(0);
            }

        });

        thread.start();
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
