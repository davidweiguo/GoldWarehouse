package com.goldwarehouse.common.transport;

/**
 * Created by David on 2016/12/20.
 */
public interface IObjectSender {
    void sendMessage(Object obj) throws Exception;
}
