package com.goldwarehouse.transport.http;

import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import com.goldwarehouse.common.event.*;
import com.goldwarehouse.transport.tools.HttpEventUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpEventHandler implements IAsyncEventBridge {
    private static final Logger log = LoggerFactory.getLogger(HttpEventHandler.class);
    private Map<String, HttpEvent> map = new ConcurrentHashMap<>();
    private IRemoteEventManager eventManager;
    private long timeout;
    private Timer timer;
    private long timerPeriod = 3000;
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                Iterator<HttpEvent> it = map.values().iterator();
                while (it.hasNext()) {
                    HttpEvent event = it.next();
                    if (event.isTimeout(timeout)) {
                        map.remove(event.id);
                        HttpEventUtil.returnTimeout(event.ctx);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    };

    private AsyncPriorityEventThread thread = new AsyncPriorityEventThread() {

        @Override
        public void onEvent(AsyncEvent event) {
            if (event.getKey() != null)
                response(event.getKey(), event);
        }

    };

    public void init() {
        thread.start();
        timer = new Timer();
        timer.schedule(task, 0, timerPeriod);
    }

    // invoke request to LTS
    public boolean addRequest(String id, HttpEvent request) throws Exception {
        if (map.containsKey(id)) {
            return false;
        }

        try {
            if (!map.containsKey(id)) {
                if (request.request(eventManager)) {
                    map.put(id, request);
                }
                return true;
            } else {
                log.info("Event alread triggered, id: " + id);
            }
        } catch (Exception e) {
            map.remove(id);
            throw e;
        }
        return false;
    }

    // response to client
    private void response(String id, AsyncEvent event) {
        HttpEvent request = map.remove(id);
        if (request == null) {
            return;
        }
        try {
            if (!request.response(event))
                map.put(id, request);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String getBridgeId() {
        return "http-bridge";
    }

    @Override
    public void onBridgeEvent(RemoteAsyncEvent event) {
        if (event.getReceiver() != null &&
                event.getReceiver().equals(getBridgeId()))
            thread.addEvent(event);
    }

    public void setEventManager(IRemoteEventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setTimerPeriod(long timerPeriod) {
        this.timerPeriod = timerPeriod;
    }
}
