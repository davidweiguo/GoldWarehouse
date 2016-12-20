package com.goldwarehouse.event;

/**
 * Created by David on 2016/12/20.
 */
public abstract class RemoteAsyncEvent extends AsyncEvent {
    String receiver;
    String sender;
    public RemoteAsyncEvent(String key, String receiver) {
        super(key);
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
