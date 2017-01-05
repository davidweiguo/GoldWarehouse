package com.goldwarehouse.common.event.account;

import com.goldwarehouse.common.event.RemoteAsyncEvent;

/**
 * Created by guo_d on 2016/12/29.
 */
public class UserLoginReplyEvent extends RemoteAsyncEvent {
    public UserLoginReplyEvent(String key, String receiver) {
        super(key, receiver);
    }
}
