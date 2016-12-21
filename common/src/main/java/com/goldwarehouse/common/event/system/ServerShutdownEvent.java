package com.goldwarehouse.common.event.system;

import com.goldwarehouse.common.event.RemoteAsyncEvent;

/**
 * Created by David on 2016/12/20.
 */
public class ServerShutdownEvent extends RemoteAsyncEvent {

    public ServerShutdownEvent() {
        super(null, null);
    }

    public ServerShutdownEvent(String key, String receiver) {
        super(key, receiver);
    }
}