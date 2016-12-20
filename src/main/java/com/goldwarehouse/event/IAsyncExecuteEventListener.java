package com.goldwarehouse.event;

/**
 * Created by David on 2016/12/20.
 */
public interface IAsyncExecuteEventListener extends IAsyncEventListener {
    IAsyncEventInbox getInbox();
}
