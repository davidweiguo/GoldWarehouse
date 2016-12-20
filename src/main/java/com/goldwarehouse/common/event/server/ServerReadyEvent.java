package com.goldwarehouse.common.event.server;

import com.goldwarehouse.event.RemoteAsyncEvent;

/**
 * Created by David on 2016/12/20.
 */
public class ServerReadyEvent extends RemoteAsyncEvent {
    boolean ready;

    public ServerReadyEvent(String key, String receiver, boolean ready) {
        super(key, receiver);
        this.ready = ready;
    }

    public ServerReadyEvent(boolean ready) {
        super(null, null);
        this.ready = ready;
    }

    public boolean isReady() {
        return ready;
    }
}