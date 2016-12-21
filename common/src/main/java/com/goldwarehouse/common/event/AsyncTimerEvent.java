package com.goldwarehouse.common.event;

/**
 * Created by David on 2016/12/21.
 */
public final class AsyncTimerEvent extends AsyncEvent {

    public AsyncTimerEvent() {
        super();
        setPriority(EventPriority.HIGH);
    }
}