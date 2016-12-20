package com.goldwarehouse.event;

/**
 * Created by David on 2016/12/20.
 */
public interface IRemoteEventManager extends IAsyncEventManager {
    public void init(String channel, String inbox)throws Exception;
    public void addEventInbox(String queue) throws Exception;
    public void addEventChannel(String channel) throws Exception;
    public void publishRemoteEvent(String channel, RemoteAsyncEvent event) throws Exception;
    public void restart() throws Exception;
    public void uninit();
    public void close() throws Exception;
    // depends on whether receiver is set, it may send in-process event or out-process event
    public void sendLocalOrRemoteEvent(RemoteAsyncEvent event) throws Exception;

    // depends on whether receiver is set to send out-process broadcast event or PtoP event
    public void sendRemoteEvent(RemoteAsyncEvent event) throws Exception;

    // send event to both in-process and out-process
    public void sendGlobalEvent(RemoteAsyncEvent event) throws Exception;
}
