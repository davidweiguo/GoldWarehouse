package com.goldwarehouse.event;

/**
 * Created by David on 2016/12/20.
 */
public class AsyncExecuteEvent extends AsyncEvent {
    IAsyncEventListener innerListener;
    AsyncEvent innerEvent;

    public AsyncExecuteEvent(IAsyncEventListener listener, AsyncEvent event) {
        super();
        this.innerListener = listener;
        this.innerEvent = event;
    }

    public IAsyncEventListener getInnerListener() {
        return innerListener;
    }

    public AsyncEvent getInnerEvent() {
        return innerEvent;
    }
}