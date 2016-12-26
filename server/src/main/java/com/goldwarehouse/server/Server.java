package com.goldwarehouse.server;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.goldwarehouse.common.IPlugin;
import com.goldwarehouse.common.SystemInfo;
import com.goldwarehouse.common.event.*;
import com.goldwarehouse.common.event.server.ServerReadyEvent;
import com.goldwarehouse.common.event.system.DuplicateSystemIdEvent;
import com.goldwarehouse.common.event.system.NodeInfoEvent;
import com.goldwarehouse.common.event.system.ServerHeartBeatEvent;
import com.goldwarehouse.common.event.system.ServerShutdownEvent;
import com.goldwarehouse.common.util.IdGenerator;
import com.goldwarehouse.common.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2016/12/20.
 */
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);
    private final int HEART_BEAT_INTERVAL = 3000; // 3000 miliseconds

    private String shutdownTime;
    private String inbox;
    private String uid;
    private String channel;
    private String nodeInfoChannel;
    private boolean serverReady;

    private List<IPlugin> plugins;
    private ReadyList readyList;

    @Autowired
    private SystemInfo systemInfo;

    @Autowired
    private IRemoteEventManager eventManager;

    @Autowired
    private ScheduleManager scheduleManager;

    private AsyncTimerEvent timerEvent = new AsyncTimerEvent();
    private ServerHeartBeatEvent heartBeat = new ServerHeartBeatEvent(null, null);
    private AsyncTimerEvent shutdownEvent = new AsyncTimerEvent();

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

            if (null != plugins) {
                for (IPlugin plugin : plugins) {
                    plugin.init();
                }
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

            // start heart beat
            scheduleManager.scheduleRepeatTimerEvent(HEART_BEAT_INTERVAL,
                    eventProcessor, timerEvent);
            registerShutdownTime();
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
            if (!event.getServer() && readyList.allUp()) {
                try {
                    eventManager.sendRemoteEvent(new ServerReadyEvent(event
                            .getKey(), event.getSender(), true));
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public void processDuplicateSystemIdEvent(DuplicateSystemIdEvent event) {
    }

    public void processServerShutdownEvent(ServerShutdownEvent event) {
    }

    public void processAsyncTimerEvent(AsyncTimerEvent event) throws Exception {
        if (event == timerEvent) {
            eventManager.publishRemoteEvent(nodeInfoChannel, heartBeat);
        } else if (event == shutdownEvent) {
            log.info("System hits end time, shutting down...");
            System.exit(0);
        }
    }

    private void registerShutdownTime() throws ParseException {
        if (null == shutdownTime) {
            return;
        }
        Date endTime = TimeUtil.parseTime("HH:mm:ss", shutdownTime);
        scheduleManager.scheduleTimerEvent(endTime, eventProcessor,
                shutdownEvent);
    }

    class ReadyList {
        Map<String, Boolean> map = new HashMap<String, Boolean>();

        ReadyList(Map<String, Boolean> map) {
            this.map = map;
            // map.put("DownStream", false);
        }

        synchronized void update(String key, boolean value) {
            if (!map.containsKey(key)) {
                return;
            }
            map.put(key, value);
            boolean now = allUp();
            if (!serverReady && now) {
                serverReady = true;
                log.info("Server is ready: " + now);
                ServerReadyEvent event = new ServerReadyEvent(now);
                eventManager.sendEvent(event);
                try {
                    eventManager.publishRemoteEvent(channel, event);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        synchronized boolean isUp(String component) {
            return map.get(component);
        }

        synchronized boolean allUp() {
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                if (!entry.getValue()) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String configFile = "server/conf/server.xml";
        String logConfigFile = "server/conf/slogback.xml";

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
