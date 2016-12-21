package com.goldwarehouse.common.event.system;

import com.goldwarehouse.event.RemoteAsyncEvent;

/**
 * Created by David on 2016/12/21.
 */
public class ServerHeartBeatEvent extends RemoteAsyncEvent {

    public ServerHeartBeatEvent(String key, String receiver) {
        super(key, receiver);
    }
}
