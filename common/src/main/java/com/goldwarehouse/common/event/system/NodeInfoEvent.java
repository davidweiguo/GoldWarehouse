package com.goldwarehouse.common.event.system;

import com.goldwarehouse.common.event.RemoteAsyncEvent;

/**
 * Created by David on 2016/12/20.
 */
public class NodeInfoEvent extends RemoteAsyncEvent {
    private Boolean server;
    private Boolean firstTime;
    private String inbox;
    private String uid;

    public NodeInfoEvent(String key, String receiver, Boolean server,
                         Boolean firstTime, String inbox, String uid) {
        super(key, receiver);
        this.server = server;
        this.firstTime = firstTime;
        this.inbox = inbox;
        this.uid = uid;
    }

    public Boolean getServer() {
        return server;
    }

    public Boolean getFirstTime() {
        return firstTime;
    }

    public String getInbox() {
        return inbox;
    }

    public String getUid() {
        return uid;
    }

}
