package com.goldwarehouse.event;

/**
 * Created by David on 2016/12/20.
 */
public interface IAsyncEventInbox {
    void addEvent(AsyncEvent event, IAsyncEventListener listener);
}
