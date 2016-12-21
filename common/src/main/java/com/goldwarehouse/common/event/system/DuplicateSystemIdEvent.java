package com.goldwarehouse.common.event.system;

import com.goldwarehouse.common.event.RemoteAsyncEvent;

/**
 * Created by David on 2016/12/20.
 */
public class DuplicateSystemIdEvent extends RemoteAsyncEvent {
    String uid;

    public DuplicateSystemIdEvent(String key, String receiver, String uid) {
        super(key, receiver);
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

}
