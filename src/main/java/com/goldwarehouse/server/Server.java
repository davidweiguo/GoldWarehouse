package com.goldwarehouse.server;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.goldwarehouse.common.SystemInfo;
import com.goldwarehouse.common.event.server.ServerReadyEvent;
import com.goldwarehouse.common.event.system.DuplicateSystemIdEvent;
import com.goldwarehouse.common.event.system.NodeInfoEvent;
import com.goldwarehouse.common.event.system.ServerShutdownEvent;
import com.goldwarehouse.event.AsyncEventProcessor;
import com.goldwarehouse.event.IAsyncEventManager;
import com.goldwarehouse.event.IRemoteEventManager;
import com.goldwarehouse.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by David on 2016/12/20.
 */
public class Server {

    private static final Logger log = LoggerFactory.getLogger(Server.class);
    private String inbox;
    private String uid;
    private String channel;
    private String nodeInfoChannel;

    @Autowired
    private SystemInfo systemInfo;

    @Autowired
    private IRemoteEventManager eventManager;

    private AsyncEventProcessor eventProcessor = new AsyncEventProcessor() {

        @Override
        public void subscribeToEvents() {
            subscribeToEvent(NodeInfoEvent.class, null);
            subscribeToEvent(DuplicateSystemIdEvent.class, null);
            subscribeToEvent(ServerShutdownEvent.class, null);
        }

        @Override
        public IAsyncEventManager getEventManager() {
            return eventManager;
        }
    };

    private void init() {
        try {
            IdGenerator.getInstance().setPrefix(systemInfo.getId() + "-");

            log.info("SystemInfo: " + systemInfo);
            this.channel = systemInfo.getEnv() + "." + systemInfo.getCategory()
                    + "." + "channel";
            this.nodeInfoChannel = systemInfo.getEnv() + "."
                    + systemInfo.getCategory() + "." + "node";
            InetAddress addr = InetAddress.getLocalHost();
            String hostName = addr.getHostName();
            this.inbox = systemInfo.getEnv() + "." + systemInfo.getCategory()
                    + "." + systemInfo.getId();
            IdGenerator.getInstance().setSystemId(this.inbox);
            this.uid = hostName + "." + IdGenerator.getInstance().getNextID();
            eventManager.init(channel, inbox);
            eventManager.addEventChannel(nodeInfoChannel);

            // subscribe to events
            eventProcessor.setHandler(this);
            eventProcessor.init();
            if (eventProcessor.getThread() != null) {
                eventProcessor.getThread().setName("Server");
            }

            // publish my node info
            NodeInfoEvent nodeInfo = new NodeInfoEvent(null, null, true, true,
                    inbox, uid);
            // Set sender as uid. This is to cater the situation when
            // duplicate inbox happened, the other node can receive the
            // NodeInfoEvent and detect it.
            // For this reason, one should never use NodeInfoEvent.getSender()
            // to
            // reply anything for this event
            nodeInfo.setSender(uid);
            eventManager.publishRemoteEvent(nodeInfoChannel, nodeInfo);
            log.info("Published my node info");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void processNodeInfoEvent(NodeInfoEvent event) throws Exception {
        // not my own message
        if (event.getFirstTime() && !event.getUid().equals(Server.this.uid)) {
            // check duplicate system id
            if (event.getServer() && event.getInbox().equals(Server.this.inbox)) {
                log.error("Duplicated system id detected: " + event.getSender());
                DuplicateSystemIdEvent de = new DuplicateSystemIdEvent(null,
                        null, event.getUid());
                de.setSender(Server.this.uid);
                eventManager.publishRemoteEvent(nodeInfoChannel, de);
            } else {
                // publish my node info
                NodeInfoEvent myInfo = new NodeInfoEvent(event.getKey(),
                        event.getSender(), true, false, Server.this.inbox,
                        Server.this.uid);
                eventManager.sendRemoteEvent(myInfo);
                log.info("Replied my nodeInfo:{}", event.getSender());
            }
            eventManager.sendRemoteEvent(new ServerReadyEvent(event
                    .getKey(), event.getSender(), true));
        }
    }

    public void processDuplicateSystemIdEvent(DuplicateSystemIdEvent event) {
    }

    public void processServerShutdownEvent(ServerShutdownEvent event) {
    }

    public void main(String[] args) {
        String configFile = "conf/server.xml";
        String logConfigFile = "conf/slogback.xml";

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            configurator.doConfigure(logConfigFile);
        } catch (JoranException je) {
            je.printStackTrace();
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);

        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
                configFile);

        // start server
        Server server = (Server) applicationContext.getBean("server");
        server.init();
    }
}
